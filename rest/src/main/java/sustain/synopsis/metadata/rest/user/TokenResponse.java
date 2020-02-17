package sustain.synopsis.metadata.rest.user;

import javax.validation.constraints.NotNull;

public class TokenResponse {

    @NotNull
    private boolean success;
    private String token;

    public TokenResponse() {
    }

    public TokenResponse(@NotNull boolean success, String description) {
        this.success = success;
        this.token = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
