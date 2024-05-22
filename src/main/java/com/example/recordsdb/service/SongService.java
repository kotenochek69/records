package com.example.recordsdb.service;

import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.mapper.ProducerMapper;
import com.example.recordsdb.mapper.SongMapper;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.model.Song;
import com.example.recordsdb.repository.GenreRepository;
import com.example.recordsdb.repository.MusicianRepository;
import com.example.recordsdb.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository repo;
    private final MusicianRepository musicianRepository;
    private final GenreRepository genreRepository;
    private final SongMapper mapper;

    public Set<SongDto> getAllSongs() {
        return repo.findAll().stream().map(mapper::map).collect(Collectors.toSet());
    }


    public SongDto createSong(String name, Integer year, Long musicianId, Long genreId) {
        var song = new Song();
        song.setName(name);
        song.setYear(year);
        var musician = musicianRepository.findById(musicianId).orElseThrow();
        song.setMusician(musician);
        var genre = genreRepository.findById(genreId).orElseThrow();
        song.setGenre(genre);
        return mapper.map(repo.save(song));
    }

    public SongDto createSong(SongDto songDto) {
        var song = new Song();
        song.setName(songDto.getName());
        song.setYear(songDto.getYear());

        // Поиск музыканта по имени
        Musician musician = musicianRepository.findByName(songDto.getMusician()).orElseThrow(() -> new IllegalArgumentException("Invalid musicians name"));

        // Поиск жанра по имени
        Genre genre = genreRepository.findByName(songDto.getGenre()).orElseThrow(() -> new IllegalArgumentException("Invalid genre name"));

        song.setMusician(musician);
        song.setGenre(genre);

        // Сохранение песни в базе данных
        return mapper.map(repo.save(song));
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public int deleteSong(Long id) {
        try {
            repo.deleteById(id);
            return 200; // Возвращаем статус 200 OK в случае успешного удаления
        } catch (Exception e) {
            e.printStackTrace();
            return 500; // Возвращаем статус 500 Internal Server Error в случае ошибки
        }
    }

}
