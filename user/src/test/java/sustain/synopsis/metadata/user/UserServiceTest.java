package sustain.synopsis.metadata.user;

import io.grpc.stub.StreamObserver;
import io.grpc.stub.StreamObservers;
import org.junit.Before;
import org.junit.Test;
import sustain.synopsis.metadata.ClusterConfig;
import sustain.synopsis.metadata.DatabaseConfig;
import sustain.synopsis.metadata.TestUtils;
import sustain.synopsis.metadata.UserServiceOuterClass;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateRequest;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateResponse;
import sustain.synopsis.metadata.UserServiceOuterClass.RegisterUserRequest;

import java.sql.SQLException;

public class UserServiceTest {

    UserService service;

    @Before
    public void before() throws SQLException, ClassNotFoundException {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setDatabase_name("synopsis_metadata");
        dbConfig.setHost("localhost");
        dbConfig.setPort(3306);
        dbConfig.setUser("root");
        dbConfig.setPassword("password");

        ClusterConfig clusterConfig = new ClusterConfig();
        clusterConfig.setDatabase(dbConfig);

        service = new UserService(clusterConfig);
    }

    @Test
    public void test() {
        String email = TestUtils.generateRandomEmail();
        String password = TestUtils.generateRandomString(8);

        RegisterUserRequest registerReq = RegisterUserRequest.newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build();

//        StreamObserver<AuthenticateResponse> obs;

//        service.authenticate(registerReq, obs);

//
//        AuthenticateRequest.newBuilder()
//                .setEmail("")
//
//        service.authenticate();

    }



}
