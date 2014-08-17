package net.masich.logserver.server.ui.service;

import net.masich.logserver.server.ui.dao.entity.User;

import java.util.Collection;

public interface UserService {

    Collection<User> findAllUsers();
    User findById(Long id);
    User findByEmail(String email);
    User create(User user);
    User update(User user);

}
