package net.masich.logserver.server.ui.dao;

import net.masich.logserver.server.ui.dao.entity.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> findAllUsers();
    User findById(Long id);
    User findByEmail(String email);
    User create(User user);
    User update(User user);
}
