package sustain.synopsis.metadata;


public class ClusterConfig {

    private ServerConfig ingest_server;
    private ServerConfig dataset_server;
    private ServerConfig user_server;
    private DatabaseConfig database;

    public ServerConfig getIngest_server() {
        return ingest_server;
    }

    public void setIngest_server(ServerConfig ingest_server) {
        this.ingest_server = ingest_server;
    }

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

}
