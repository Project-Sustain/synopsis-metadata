package sustain.synopsis.metadata.query.data;

import sustain.synopsis.metadata.query.data.cassandra.CassandraStrand;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncDataConnection {

    private DataConnection dataConnection;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public AsyncDataConnection(DataConnection dataConnection) {
        this.dataConnection = dataConnection;
    }

    public Future<List<CassandraStrand>> getStrandsForQuery(String query) {
        return executorService.submit(() -> dataConnection.getStrandsForQuery(query));
    }

}
