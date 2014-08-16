package net.masich.logserver.server.ui.model;

public class SignInResponse {

    private boolean status;
    private String errorMessage;

    public SignInResponse(boolean status) {
        this(status, null);
    }

    public SignInResponse(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
