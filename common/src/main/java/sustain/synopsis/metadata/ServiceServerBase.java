package sustain.synopsis.metadata;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.grpc.Server;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class ServiceServerBase {

    protected Server server;
    protected ClusterConfig clusterConfig;

    public static ClusterConfig getClusterConfigFromPath(String configPath) throws IOException {
        YAMLMapper mapper = new YAMLMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File(configPath), ClusterConfig.class);
    }

    public void start() throws IOException {
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    public void shutdown() {
        server.shutdown();
    }

    public void blockUntilShutdown() throws InterruptedException {
        server.awaitTermination();
    }

}
