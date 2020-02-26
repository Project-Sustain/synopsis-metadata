package sustain.synopsis.metadata.user;

import io.grpc.stub.StreamObserver;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.HealthCheck.HealthCheckRequest;
import sustain.synopsis.metadata.HealthCheck.HealthCheckResponse;
import sustain.synopsis.metadata.util.JwtTokenUtil;
import sustain.synopsis.metadata.UserServiceGrpc;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateRequest;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateResponse;
import sustain.synopsis.metadata.UserServiceOuterClass.RegisterUserRequest;
import sustain.synopsis.metadata.UserServiceOuterClass.RegisterUserResponse;

import java.sql.SQLException;

public class UserService extends UserServiceGrpc.UserServiceImplBase {

    private final UserDataSource dataSource;

    public UserService(ClusterConfig config) throws SQLException, ClassNotFoundException {
         this.dataSource = new MySqlUserDataSource(config.getDatabase());
    }

    @Override
    public void checkHealth(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        responseObserver.onNext(HealthCheckResponse.newBuilder().setIsHealthy(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void registerUser(RegisterUserRequest request, StreamObserver<RegisterUserResponse> responseObserver) {
        try {
            UserCredentials credentials = UserCredentials.createUserCredential(request.getEmail(),request.getPassword());
            dataSource.registerUser(credentials);
            responseObserver.onNext(RegisterUserResponse.newBuilder().setSuccess(true).build());
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void authenticate(AuthenticateRequest request, StreamObserver<AuthenticateResponse> responseObserver) {
        try {
            UserCredentials credentials = UserCredentials.createUserCredential(request.getEmail(),request.getPassword());
            UserAuthenticationResult result = dataSource.checkUserInfo(credentials);

            AuthenticateResponse resp = AuthenticateResponse.newBuilder()
                    .setToken(JwtTokenUtil.createAccessToken(request.getEmail(), result.isAdmin()))
                    .setSuccess(true)
                    .build();

            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }

    }
}
