package sustain.synopsis.metadata.config;


public class ClusterConfig {

    private ServerConfig dataset_server;
    private ServerConfig user_server;
    private DatabaseConfig database;
    private CassandraConfig cassandra;

    public ServerConfig getDataset_server() {
        return dataset_server;
    }

    public void setDataset_server(ServerConfig dataset_server) {
        this.dataset_server = dataset_server;
    }

    public ServerConfig getUser_server() {
        return user_server;
    }

    public void setUser_server(ServerConfig user_server) {
        this.user_server = user_server;
    }

    public DatabaseConfig getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseConfig database) {
        this.database = database;
    }

    public CassandraConfig getCassandra() {
        return cassandra;
    }

    public void setCassandra(CassandraConfig cassandra) {
        this.cassandra = cassandra;
    }

}
