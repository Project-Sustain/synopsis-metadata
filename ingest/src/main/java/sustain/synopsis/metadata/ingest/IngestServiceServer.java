package sustain.synopsis.metadata.ingest;

import io.grpc.ServerBuilder;
import sustain.synopsis.metadata.ServiceServerBase;

public class IngestServiceServer extends ServiceServerBase {

    public IngestServiceServer(String configPath) throws Exception {
        this.clusterConfig = ServiceServerBase.getClusterConfigFromPath(configPath);

        this.server = ServerBuilder
                .forPort(clusterConfig.getIngest_server().getPort())
                .addService(new IngestService(clusterConfig))
                .build();
    }

    public static void main(String[] args) {
        String configPath = args[0];
        try {
            IngestServiceServer server = new IngestServiceServer(configPath);
            server.start();
            server.blockUntilShutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
