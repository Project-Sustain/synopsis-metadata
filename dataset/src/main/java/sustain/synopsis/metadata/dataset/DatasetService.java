package sustain.synopsis.metadata.dataset;

import io.grpc.stub.StreamObserver;
import sustain.synopsis.metadata.ClusterConfig;
import sustain.synopsis.metadata.DatasetServiceGrpc;
import java.sql.SQLException;
import static sustain.synopsis.metadata.DatasetServiceOuterClass.*;

public class DatasetService extends DatasetServiceGrpc.DatasetServiceImplBase {

    private final DatasetDataSource dataSource;

    public DatasetService(ClusterConfig config) throws SQLException, ClassNotFoundException {
         this.dataSource = new MySqlDatasetDataSource(config.getDatabase());
    }

    @Override
    public void registerDataset(RegisterDatasetRequest request, StreamObserver<RegisterDatasetResponse> responseObserver) {
        try {
            dataSource.insertDataset(request.getDatasetInfo());
            RegisterDatasetResponse resp = RegisterDatasetResponse.newBuilder().build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDatasetInfo(GetDatasetInfoRequest request, StreamObserver<GetDatasetInfoResponse> responseObserver) {
        try {
            GetDatasetInfoResponse resp = GetDatasetInfoResponse.newBuilder()
                    .setDatasetInfo(dataSource.getDatasetInfo(request.getId()))
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }

    @Override
    public void getDatasetInfos(GetDatasetInfosRequest request, StreamObserver<GetDatasetInfosResponse> responseObserver) {
        try {
            GetDatasetInfosResponse resp = GetDatasetInfosResponse.newBuilder()
                    .addAllInfo(dataSource.getDatasetInfos())
                    .build();

            responseObserver.onNext(resp);
            responseObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
            responseObserver.onError(e);
        }
    }
}
