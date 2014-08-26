package net.masich.logserver.server.ui.domain.logmessage.repository;

import net.masich.logserver.server.ui.domain.logmessage.entity.LogMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogMessageRepository extends JpaRepository<LogMessageEntity, Long> {
}
