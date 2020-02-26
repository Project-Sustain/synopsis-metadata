package sustain.synopsis.metadata.user;

import sustain.synopsis.metadata.config.DatabaseConfig;
import sustain.synopsis.metadata.MySqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDataSource extends MySqlDataSource implements UserDataSource {

    public MySqlUserDataSource(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        super(db);
    }

    @Override
    public void registerUser(UserCredentials credentials) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUE(?,?,?,?,NOW(),NOW())");
        pstmt.setString(1, credentials.getEmail());
        pstmt.setBytes(2, credentials.getSecret());
        pstmt.setBoolean(3, false); // has_verified_email
        pstmt.setBoolean(4, false); // is_admin

        pstmt.execute();
    }

    @Override
    public UserAuthenticationResult checkUserInfo(UserCredentials credentials) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE email=? AND secret=?");
            pstmt.setString(1,credentials.getEmail());
            pstmt.setBytes(2,credentials.getSecret());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("is_admin");
                return new UserAuthenticationResult(true, isAdmin);
            }
        } catch (SQLException e) {}
        return new UserAuthenticationResult(false, false);
    }


}
