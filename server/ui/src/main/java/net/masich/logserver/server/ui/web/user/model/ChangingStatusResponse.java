package net.masich.logserver.server.ui.web.user.model;

import net.masich.logserver.server.ui.web.user.dto.UserDto;

public class ChangingStatusResponse {

    private boolean status;
    private UserDto data;

    public ChangingStatusResponse() {
    }

    public ChangingStatusResponse(boolean status, UserDto data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public UserDto getData() {
        return data;
    }

}
