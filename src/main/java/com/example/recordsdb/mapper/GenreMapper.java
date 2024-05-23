package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.GenreDto;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Song;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreDto map(Genre genre) {
        if (genre == null) return null;
        var dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setSongs(genre.getSongs().stream().map(Song::getName).toList());
        dto.setRecords(genre.getRecords().stream().map(RecordEntity::getName).collect(Collectors.toSet()));
        return dto;
    }

    public Genre map(GenreDto dto) {
        var genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        genre.setSongs(dto.getSongs().stream().map(name->{
            var entity = new Song();
            entity.setName(name);
            return entity;
        }).toList());
        genre.setRecords(dto.getRecords().stream().map(name->{
            var entity = new RecordEntity();
            entity.setName(name);
            return entity;
        }).collect(Collectors.toSet()));

        return genre;
    }

}
