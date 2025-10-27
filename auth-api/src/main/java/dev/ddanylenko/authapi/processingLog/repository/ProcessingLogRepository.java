package dev.ddanylenko.authapi.processingLog.repository;

import dev.ddanylenko.authapi.processingLog.model.ProcessingLogEntity;
import dev.ddanylenko.authapi.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessingLogRepository extends JpaRepository<ProcessingLogEntity, UUID> {

}
