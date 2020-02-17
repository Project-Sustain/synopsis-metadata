package sustain.synopsis.metadata.ingest;

import io.grpc.stub.StreamObserver;
import sustain.synopsis.metadata.ClusterConfig;
import sustain.synopsis.metadata.IngestServiceGrpc;
import sustain.synopsis.metadata.IngestServiceOuterClass;

import java.sql.SQLException;

public class IngestService extends IngestServiceGrpc.IngestServiceImplBase {

    private final IngestDataSource provider;

    public IngestService(ClusterConfig config) throws SQLException, ClassNotFoundException {
        provider = new MySqlIngestDataSource(config.getDatabase());
    }

    @Override
    public void createSession(IngestServiceOuterClass.CreateSessionRequest request, StreamObserver<IngestServiceOuterClass.CreateSessionResponse> responseObserver) {
        try {
            provider.insertSession(request.getDatasetId(),request.getClientId());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
