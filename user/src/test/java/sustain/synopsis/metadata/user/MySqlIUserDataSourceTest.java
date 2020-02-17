package sustain.synopsis.metadata.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sustain.synopsis.metadata.DatabaseConfig;
import sustain.synopsis.metadata.TestUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class MySqlIUserDataSourceTest {

    MySqlUserDataSource ds;

    @Before
    public void before() throws SQLException, ClassNotFoundException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDatabase_name("synopsis_metadata");
        dbConfig.setHost("localhost");
        dbConfig.setPort(3306);
        dbConfig.setUser("root");
        dbConfig.setPassword("password");

        ds = new MySqlUserDataSource(dbConfig);
    }

    @Test
    public void testRegisterUser() throws SQLException, IOException, NoSuchAlgorithmException {
        String email = TestUtils.generateRandomEmail();
        String password = TestUtils.generateRandomString(8);

        UserCredentials credentials = UserCredentials.createUserCredential(email,password);
        ds.registerUser(credentials);
    }

    @Test
    public void testAuthenticateUser() throws SQLException, IOException, NoSuchAlgorithmException {
        String email = TestUtils.generateRandomEmail();
        String password = TestUtils.generateRandomString(8);

        UserCredentials credentials = UserCredentials.createUserCredential(email,password);
        ds.registerUser(credentials);

        UserAuthenticationResult result = ds.checkUserInfo(credentials);
        Assert.assertTrue(result.isAuthenticated());
        Assert.assertFalse(result.isAdmin());
    }
    
}
