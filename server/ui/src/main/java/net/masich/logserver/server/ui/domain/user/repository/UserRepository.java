package net.masich.logserver.server.ui.domain.user.repository;

import net.masich.logserver.server.ui.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
