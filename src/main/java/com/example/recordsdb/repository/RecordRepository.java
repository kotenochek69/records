package com.example.recordsdb.repository;

import com.example.recordsdb.dto.RecordDto;
import com.example.recordsdb.model.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    Optional<RecordEntity> findByName(RecordDto record);
}
