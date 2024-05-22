package com.example.recordsdb.repository;

import com.example.recordsdb.dto.ProducerDto;
import com.example.recordsdb.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(ProducerDto producer);
}
