package net.masich.logserver.server.ui.dao;

import net.masich.logserver.server.ui.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
