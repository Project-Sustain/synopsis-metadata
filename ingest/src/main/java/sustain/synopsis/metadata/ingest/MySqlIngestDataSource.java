package sustain.synopsis.metadata.ingest;

import sustain.synopsis.metadata.DatabaseConfig;
import sustain.synopsis.metadata.MySqlDataSource;

import java.sql.*;
import java.time.Instant;

public class MySqlIngestDataSource extends MySqlDataSource implements IngestDataSource {

    public MySqlIngestDataSource(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        super(db);
    }

    private int getMaxSessionId(String datasetId) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT IFNULL(MAX(session_id),-1) FROM ingest_session WHERE dataset_id=?");
        pstmt.setString(1, datasetId);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public int insertSession(String datasetId, String clientId) throws SQLException {
        int nextSessionId = 1 + getMaxSessionId(datasetId);

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ingest_session VALUE(?,?,?,?)");
        pstmt.setString(1, datasetId);
        pstmt.setInt(2, nextSessionId);
        pstmt.setString(3, clientId);
        pstmt.setTimestamp(4, Timestamp.from(Instant.now()));

        pstmt.execute();

        return nextSessionId;
    }



}
