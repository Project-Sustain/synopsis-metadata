package sustain.synopsis.metadata.rest;

import javax.validation.constraints.NotNull;

public class BasicResponse {

    @NotNull
    private boolean success;
    private String description;

    public BasicResponse() {

    }

    public BasicResponse(@NotNull boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
