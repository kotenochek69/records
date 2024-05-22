package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.GenreDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Song;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SongMapper {
    public SongDto map(Song song) {
        if (song == null) return null;
        var dto = new SongDto();
        dto.setId(song.getId());
        dto.setName(song.getName());
        dto.setYear(song.getYear());
        dto.setRecords(song.getRecords().stream().map(RecordEntity::getName).collect(Collectors.toSet()));
        try {
            dto.setMusician(song.getMusician().getName());
        } catch (Exception ignored){};
        try {
            dto.setGenre(song.getGenre().getName());
        } catch (Exception ignored){};
        return dto;
    }

    public Song map(SongDto dto) {
        var song = new Song();
        song.setId(dto.getId());
        song.setName(dto.getName());
        song.setYear(dto.getYear());
        song.setMusician(new Musician().setName(dto.getName()));
        song.setGenre(new Genre().setName(dto.getGenre()));
        song.setRecords(dto.getRecords().stream().map(name->{
            var entity = new RecordEntity();
            entity.setName(name);
            return entity;
        }).toList());

        return song;
    }
}
