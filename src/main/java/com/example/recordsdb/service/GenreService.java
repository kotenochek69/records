package com.example.recordsdb.service;

import com.example.recordsdb.dto.GenreDto;
import com.example.recordsdb.mapper.CustomerMapper;
import com.example.recordsdb.mapper.GenreMapper;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository repo;
    private final GenreMapper mapper;

    public List<GenreDto> getAllGenres() {
        return repo.findAll().stream().map(mapper::map).toList();
    }

}
