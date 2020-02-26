package sustain.synopsis.metadata.dataset;

import sustain.synopsis.metadata.DatasetServiceOuterClass.Session;
import sustain.synopsis.metadata.config.DatabaseConfig;
import sustain.synopsis.metadata.MySqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DatasetDataSource extends MySqlDataSource {

    public DatasetDataSource(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        super(db);
    }

    public void insertDataset(String id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO dataset VALUE(?,NOW())");
        pstmt.setString(1, id);
        pstmt.execute();
    }

    public int insertSession(String datasetId, long temporalBracketLength, int geohashLength, String binConfig) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO session VALUE(?,?,?,?,?)");
        pstmt.setString(1, datasetId);
        pstmt.setLong(2, temporalBracketLength);
        pstmt.setInt(3,geohashLength);
        pstmt.setString(4, binConfig);
        pstmt.setTimestamp(5, Timestamp.from(Instant.now()));
        pstmt.execute();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT MAX(session_id) from session where dataset_id=?");
        preparedStatement.setString(1,datasetId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(0);
    }

    private static Session readSessionFromResultSet(ResultSet rs) throws SQLException {
        return Session.newBuilder()
                .setSessionId(rs.getInt("session_id"))
                .setTemporalBracketLength(rs.getLong("temoporal_bracket_length"))
                .setGeohashLength(rs.getInt("geohash_length"))
                .setBinConfig(rs.getString("bin_config"))
                .build();
    }

    public List<Session> getDatasetSessions(String datasetId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(
                "SELECT session_id, temporal_bracket_length, geohash_length, bin_config from session where dataset_id=?");
        ResultSet resultSet = pstmt.executeQuery();

        ArrayList<Session> sessions = new ArrayList<>();
        while (resultSet.next()) {
            sessions.add(readSessionFromResultSet(resultSet));
        }
        return sessions;
    }

}
