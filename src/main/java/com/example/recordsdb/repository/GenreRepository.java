package com.example.recordsdb.repository;

import com.example.recordsdb.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository  extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String genre);
}
