package net.masich.logserver.server.ui.service.impl;

import net.masich.logserver.server.ui.dao.UserDao;
import net.masich.logserver.server.ui.dao.entity.User;
import net.masich.logserver.server.ui.service.UserService;
import net.masich.logserver.server.ui.service.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        try {
            return userDao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFound(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFound(e);
        }
    }

    @Override
    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userDao.update(user);
    }

}
