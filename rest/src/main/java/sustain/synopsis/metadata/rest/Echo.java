package sustain.synopsis.metadata.rest;

import javax.validation.constraints.NotNull;

public class Echo {
    @NotNull
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
