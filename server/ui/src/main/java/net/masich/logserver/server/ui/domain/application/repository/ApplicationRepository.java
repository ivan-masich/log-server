package net.masich.logserver.server.ui.domain.application.repository;

import net.masich.logserver.server.ui.domain.application.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
