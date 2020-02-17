package sustain.synopsis.metadata.rest.user;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.*;
import sustain.synopsis.metadata.UserServiceGrpc;
import sustain.synopsis.metadata.UserServiceGrpc.UserServiceBlockingStub;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateRequest;
import sustain.synopsis.metadata.UserServiceOuterClass.AuthenticateResponse;
import sustain.synopsis.metadata.UserServiceOuterClass.RegisterUserRequest;
import sustain.synopsis.metadata.UserServiceOuterClass.RegisterUserResponse;

import javax.validation.Valid;

@RestController
public class UserController {

    private final String host = "localhost";
    private final int port = 44097;
    private Channel channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .build();
    private UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);

    @RequestMapping(path="/api/users", method=RequestMethod.POST)
    @ResponseBody
    public void registerUser(@RequestBody @Valid UserCredentials user) {
        RegisterUserRequest req = RegisterUserRequest.newBuilder()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();

        RegisterUserResponse resp = stub.registerUser(req);
    }

    @RequestMapping(path="/api/authenticate", method=RequestMethod.POST)
    @ResponseBody
    public String authenticate(@RequestBody @Valid UserCredentials user) {
        AuthenticateRequest req = AuthenticateRequest.newBuilder()
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();

        AuthenticateResponse resp = stub.authenticate(req);

        return resp.getToken();
    }

}
