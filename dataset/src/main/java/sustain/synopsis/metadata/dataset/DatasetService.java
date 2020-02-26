package sustain.synopsis.metadata.dataset;

import io.grpc.stub.StreamObserver;
import sustain.synopsis.metadata.HealthCheck;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.DatasetServiceGrpc;

import java.sql.SQLException;
import static sustain.synopsis.metadata.DatasetServiceOuterClass.*;

public class DatasetService extends DatasetServiceGrpc.DatasetServiceImplBase {

    private final DatasetDataSource dataSource;

    public DatasetService(ClusterConfig config) throws SQLException, ClassNotFoundException {
         this.dataSource = new DatasetDataSource(config.getDatabase());
    }

    @Override
    public void registerDataset(RegisterDatasetRequest request, StreamObserver<RegisterDatasetResponse> responseObserver) {
        try {
            dataSource.insertDataset(request.getDatasetId());
            RegisterDatasetResponse resp = RegisterDatasetResponse.newBuilder().build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void createIngestSession(CreateIngestSessionRequest request, StreamObserver<CreateIngestSessionResponse> responseObserver) {
        try {
            dataSource.insertSession(
                    request.getDatasetId(),
                    request.getTemporalBracketLength(),
                    request.getGeohashLength(),
                    request.getBinConfig());

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDatasetSessions(GetDatasetSessionsRequest request, StreamObserver<GetDatasetSessionsResponse> responseObserver) {
        try {
            GetDatasetSessionsResponse response = GetDatasetSessionsResponse.newBuilder()
                    .addAllSession(dataSource.getDatasetSessions(request.getDatasetId()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void checkHealth(HealthCheck.HealthCheckRequest request, StreamObserver<HealthCheck.HealthCheckResponse> responseObserver) {
        super.checkHealth(request, responseObserver);
    }

}
