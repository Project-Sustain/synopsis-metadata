package sustain.synopsis.metadata.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtTokenUtil {

    private static final String secret = "secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String createAccessToken(String email, boolean isAdmin) throws JWTCreationException {
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("email", email)
                .withClaim("admin",isAdmin)
                .sign(algorithm);
    }

    public static DecodedSynopsisToken decodeToken(String token) {
//        logger.info("verifyAccessToken token: "+token);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            Claim email = jwt.getClaim("email");
            Claim admin = jwt.getClaim("admin");

            DecodedSynopsisToken decodedToken = new DecodedSynopsisToken();
            decodedToken.isVerified = true;
            if (!email.isNull() && (email.asString() != null)) {
                decodedToken.setEmail(email.asString());
            }
            if (!admin.isNull() && (admin.asBoolean() != null) ) {
                decodedToken.setAdmin(admin.asBoolean());
            }
            return decodedToken;

        } catch (JWTVerificationException exception){
            //Invalid signature/claims
//            logger.info("verifyAccessToken token was invalid");
            return new DecodedSynopsisToken();
        }
    }

    public static class DecodedSynopsisToken {
        private boolean isVerified = false;
        private String email;
        private boolean admin = false;

        public boolean isVerified() {
            return isVerified;
        }

        public void setVerified(boolean verified) {
            isVerified = verified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }

}
