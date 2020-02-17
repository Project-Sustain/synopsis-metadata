package sustain.synopsis.metadata.dataset;

import sustain.synopsis.metadata.DatabaseConfig;
import sustain.synopsis.metadata.DatasetInfoOuterClass;
import sustain.synopsis.metadata.DatasetInfoOuterClass.DatasetInfo;
import sustain.synopsis.metadata.MySqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MySqlDatasetDataSource extends MySqlDataSource implements DatasetDataSource {

    public MySqlDatasetDataSource(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        super(db);
    }

    @Override
    public void insertDataset(DatasetInfo datasetInfo) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO dataset VALUE(?,?,?,?,?,NOW())");

        pstmt.setString(1,datasetInfo.getDatasetId());
        pstmt.setLong(2,datasetInfo.getTemporalBracketLength());
        pstmt.setInt(3,datasetInfo.getGeohashLength());
        pstmt.setString(4,datasetInfo.getBinConfig());
        pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

        pstmt.execute();
    }

    DatasetInfo readDatasetInfoFromResultSet(ResultSet rs) throws SQLException {
        return DatasetInfo.newBuilder()
                .setDatasetId(rs.getString("id"))
                .setTemporalBracketLength(rs.getLong("temporal_bracket_length"))
                .setGeohashLength(rs.getInt("geohash_length"))
                .setBinConfig(rs.getString("bin_config"))
                .build();
    }

    @Override
    public DatasetInfo getDatasetInfo(String id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dataset WHERE id=?");
        pstmt.setString(1,id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return readDatasetInfoFromResultSet(rs);
    }

    @Override
    public List<DatasetInfo> getDatasetInfos() throws Exception {
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dataset");
        ResultSet rs = pstmt.executeQuery();
        ArrayList<DatasetInfo> infos = new ArrayList<>();
        while (rs.next()) {
            infos.add(readDatasetInfoFromResultSet(rs));
        }
        return infos;
    }


}
