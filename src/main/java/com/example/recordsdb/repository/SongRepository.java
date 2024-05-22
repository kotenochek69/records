package com.example.recordsdb.repository;

import com.example.recordsdb.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
