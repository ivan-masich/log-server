package net.masich.logserver.server.ui.web.user.model;

public class DeleteResponse {

    private Long id;
    private boolean status;

    public DeleteResponse() {
    }

    public DeleteResponse(Long id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

}
