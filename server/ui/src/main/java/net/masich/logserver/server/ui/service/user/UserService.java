package net.masich.logserver.server.ui.service.user;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;

import java.util.Collection;

public interface UserService {

    Collection<UserEntity> findAll();
    UserEntity findById(Long id);
    UserEntity findByEmail(String email);
    UserEntity create(UserEntity user);
    UserEntity update(UserEntity user);

}
