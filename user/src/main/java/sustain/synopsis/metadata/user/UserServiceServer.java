package sustain.synopsis.metadata.user;

import io.grpc.ServerBuilder;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.ServiceServerBase;

import java.sql.SQLException;

public class UserServiceServer extends ServiceServerBase {

    public UserServiceServer(ClusterConfig config) throws SQLException, ClassNotFoundException {
        this.clusterConfig = config;
        this.server = ServerBuilder
                .forPort(clusterConfig.getUser_server().getPort())
                .addService(new UserService(clusterConfig))
                .build();
    }

    public static void main(String[] args) {
        String configPath = args[0];
        try {
            UserServiceServer server = new UserServiceServer(ServiceServerBase.getClusterConfigFromPath(configPath));
            server.start();
            server.blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
