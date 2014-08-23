package net.masich.logserver.server.ui.web.user.model;

import net.masich.logserver.server.ui.web.user.dto.UserDto;

import java.util.Collection;

public class UsersListResponse {

    private Collection<UserDto> users;

    public UsersListResponse(Collection<UserDto> users) {
        this.users = users;
    }

    public Collection<UserDto> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDto> users) {
        this.users = users;
    }

}
