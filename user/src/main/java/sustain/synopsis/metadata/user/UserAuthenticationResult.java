package sustain.synopsis.metadata.user;

public class UserAuthenticationResult {

    private boolean authenticated;
    private boolean isAdmin;

    public UserAuthenticationResult(boolean authenticated, boolean isAdmin) {
        this.authenticated = authenticated;
        this.isAdmin = isAdmin;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
