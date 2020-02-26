package sustain.synopsis.metadata.query;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import sustain.synopsis.metadata.DatasetServiceGrpc;
import sustain.synopsis.metadata.QueryServiceGrpc;
import sustain.synopsis.metadata.UserServiceGrpc;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.query.data.AsyncDataConnection;
import sustain.synopsis.metadata.query.data.DataConnection;
import sustain.synopsis.metadata.query.data.cassandra.CassandraConnection;
import sustain.synopsis.metadata.query.data.cassandra.CassandraDataConnection;

public class Cluster {

    private DatasetServiceGrpc.DatasetServiceFutureStub datasetStub;
    private AsyncDataConnection dataConnection;

    public Cluster(ClusterConfig config) {
        Channel datasetChannel = ManagedChannelBuilder.forAddress(config.getDataset_server().getHost(), config.getDataset_server().getPort())
                .usePlaintext()
                .build();
        this.datasetStub = DatasetServiceGrpc.newFutureStub(datasetChannel);

        CassandraConnection cassandraConnection = new CassandraConnection(config.getCassandra().getHost(), config.getCassandra().getKeyspace());
        this.dataConnection = new AsyncDataConnection(new CassandraDataConnection(cassandraConnection));
    }

    public DatasetServiceGrpc.DatasetServiceFutureStub getDatasetStub() {
        return datasetStub;
    }

    public AsyncDataConnection getDataConnection() {
        return dataConnection;
    }

}
