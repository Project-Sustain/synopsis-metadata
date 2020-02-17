package sustain.synopsis.metadata.user;

import sustain.synopsis.metadata.HashAlgorithm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class UserCredentials {

    private String email;
    private byte[] secret;

    public UserCredentials(String email, byte[] secret) {
        this.email = email;
        this.secret = secret;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getSecret() {
        return secret;
    }

    public static UserCredentials createUserCredential(String email, String password) throws IOException, NoSuchAlgorithmException {
        byte[] secret = HashAlgorithm.generateUserSecret(email, password);
        return new UserCredentials(email, secret);
    }

}
