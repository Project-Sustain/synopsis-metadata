package sustain.synopsis.metadata.query;

import io.grpc.ServerBuilder;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.ServiceServerBase;

import java.sql.SQLException;

public class QueryServiceServer extends ServiceServerBase {

    public QueryServiceServer(ClusterConfig config) throws SQLException, ClassNotFoundException {
        this.clusterConfig = config;
        this.server = ServerBuilder
                .forPort(clusterConfig.getDataset_server().getPort())
                .addService(new QueryService(clusterConfig))
                .build();
    }

    public static void main(String[] args) {
        String configPath = args[0];
        try {
            QueryServiceServer server = new QueryServiceServer(ServiceServerBase.getClusterConfigFromPath(configPath));
            server.start();
            server.blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
