package sustain.synopsis.metadata.dataset;

import io.grpc.ServerBuilder;
import sustain.synopsis.metadata.ClusterConfig;
import sustain.synopsis.metadata.ServiceServerBase;

import java.sql.SQLException;

public class DatasetServiceServer extends ServiceServerBase {

    public DatasetServiceServer(ClusterConfig config) throws SQLException, ClassNotFoundException {
        this.clusterConfig = config;
        this.server = ServerBuilder
                .forPort(clusterConfig.getDataset_server().getPort())
                .addService(new DatasetService(clusterConfig))
                .build();
    }

    public static void main(String[] args) {
        String configPath = args[0];
        try {
            DatasetServiceServer server = new DatasetServiceServer(ServiceServerBase.getClusterConfigFromPath(configPath));
            server.start();
            server.blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
