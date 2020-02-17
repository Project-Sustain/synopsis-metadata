package sustain.synopsis.metadata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashAlgorithm {

    // might want to cache a message digest instance per thread
    public static byte[] generateUserSecret(String email, String password) throws IOException, NoSuchAlgorithmException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(email.getBytes(StandardCharsets.UTF_8));
            out.write(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = out.toByteArray();
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(bytes);
            return hash;
    }

}
