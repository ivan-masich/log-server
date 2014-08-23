package net.masich.logserver.server.ui.service.user.impl;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import net.masich.logserver.server.ui.domain.user.repository.UserRepository;
import net.masich.logserver.server.ui.service.user.UserService;
import net.masich.logserver.server.ui.service.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Collection<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        UserEntity user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFound();
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFound();
        }

        return user;
    }

    @Override
    @Transactional
    public UserEntity create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserEntity update(UserEntity user) {
        UserEntity entity = userRepository.findOne(user.getId());
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(entity.getPassword());
        }
        user.setActive(entity.isActive());
        return userRepository.save(user);
    }

}
