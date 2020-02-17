package sustain.synopsis.metadata.user;

import java.sql.SQLException;

public interface UserDataSource {

    public void registerUser(UserCredentials credentials) throws Exception;
    public UserAuthenticationResult checkUserInfo(UserCredentials credentials);

}
