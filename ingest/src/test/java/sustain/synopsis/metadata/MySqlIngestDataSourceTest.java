package sustain.synopsis.metadata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sustain.synopsis.metadata.ingest.MySqlIngestDataSource;

import java.sql.SQLException;

public class MySqlIngestDataSourceTest {

    MySqlIngestDataSource ds;

    @Before
    public void before() throws SQLException, ClassNotFoundException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDatabase_name("synopsis_metadata");
        dbConfig.setHost("localhost");
        dbConfig.setPort(3306);
        dbConfig.setUser("root");
        dbConfig.setPassword("password");

        ds = new MySqlIngestDataSource(dbConfig);
    }

    @Test
    public void testGetNextSessionId() throws SQLException, InterruptedException {
        String dbId = TestUtils.generateRandomString(16);
        String clientId = "abcdefg";

        Assert.assertEquals(0, ds.insertSession(dbId,clientId));
        Assert.assertEquals(1, ds.insertSession(dbId,clientId));
        Assert.assertEquals(2, ds.insertSession(dbId,clientId));
    }
    
}
