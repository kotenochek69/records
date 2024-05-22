package com.example.recordsdb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.recordsdb.model.Musician;

import java.util.Optional;

public interface MusicianRepository extends JpaRepository<Musician, Long> {
    Optional<Musician> findByName(String musician);
}
