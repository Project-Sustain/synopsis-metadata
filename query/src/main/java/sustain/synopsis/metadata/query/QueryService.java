package sustain.synopsis.metadata.query;

import io.grpc.stub.StreamObserver;
import sustain.synopsis.metadata.*;
import sustain.synopsis.metadata.config.ClusterConfig;

import java.sql.SQLException;

public class QueryService extends QueryServiceGrpc.QueryServiceImplBase {

    private final QueryDataSource dataSource;

    public QueryService(ClusterConfig config) throws SQLException, ClassNotFoundException {
         this.dataSource = new MySqlQueryDataSource(config.getDatabase());
    }

    @Override
    public void checkHealth(HealthCheck.HealthCheckRequest request, StreamObserver<HealthCheck.HealthCheckResponse> responseObserver) {
        super.checkHealth(request, responseObserver);
    }

    @Override
    public void query(QueryServiceOuterClass.QueryRequest request, StreamObserver<QueryServiceOuterClass.QueryResponse> responseObserver) {
        super.query(request, responseObserver);
    }




}
