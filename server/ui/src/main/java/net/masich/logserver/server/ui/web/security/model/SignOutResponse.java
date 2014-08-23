package net.masich.logserver.server.ui.web.security.model;

public class SignOutResponse {

    private boolean status;

    public SignOutResponse(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

}
