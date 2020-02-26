package sustain.synopsis.metadata;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sustain.synopsis.metadata.DatasetServiceGrpc.DatasetServiceBlockingStub;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.config.DatabaseConfig;
import sustain.synopsis.metadata.config.ServerConfig;
import sustain.synopsis.metadata.dataset.DatasetServiceServer;
import sustain.synopsis.metadata.util.TestUtils;

import static sustain.synopsis.metadata.DatasetServiceOuterClass.*;

public class Test1 {

    DatasetServiceServer server;
    DatasetServiceBlockingStub stub;

    @Before
    public void before() throws Exception {
        ClusterConfig config = new ClusterConfig();

        ServerConfig datasetServer = new ServerConfig();
        datasetServer.setHost("localhost");
        datasetServer.setPort(44098);
        config.setDataset_server(datasetServer);

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setDatabase_name("synopsis_metadata");
        databaseConfig.setHost("localhost");
        databaseConfig.setPort(3306);
        databaseConfig.setUser("root");
        databaseConfig.setPassword("password");
        config.setDatabase(databaseConfig);

        server = new DatasetServiceServer(config);
        server.start();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(config.getDataset_server().getHost(),config.getDataset_server().getPort())
                .usePlaintext()
                .build();

        stub = DatasetServiceGrpc.newBlockingStub(channel);
    }

//    @Test
//    public void testRegisterDataset() throws Exception {
//        DatasetInfoOuterClass.DatasetInfo info = DatasetInfoOuterClass.DatasetInfo.newBuilder()
//                .setDatasetId(TestUtils.generateRandomString(16))
//                .setBinConfig("myBinConfig")
//                .setTemporalBracketLength(100000)
//                .setGeohashLength(3)
//                .build();
//
//        RegisterDatasetRequest req = RegisterDatasetRequest.newBuilder()
//                .setDatasetInfo(info)
//                .build();
//
//        RegisterDatasetResponse resp = stub.registerDataset(req);
//    }

    @After
    public void after() {
        server.shutdown();
    }

}
