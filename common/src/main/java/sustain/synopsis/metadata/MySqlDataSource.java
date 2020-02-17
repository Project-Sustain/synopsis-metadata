package sustain.synopsis.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDataSource {

    protected Connection conn;
    public MySqlDataSource(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        conn = createConnection(db);
    }

    private static Connection createConnection(DatabaseConfig db) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connStr = String.format("jdbc:mysql://%s:%d/%s?serverTimezone=America/Denver",db.getHost(), db.getPort(), db.getDatabase_name());
        return DriverManager.getConnection(connStr, db.getUser(),db.getPassword());
    }

}
