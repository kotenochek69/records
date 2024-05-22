package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.RecordDto;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Song;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.model.RecordEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecordMapper {
    public RecordDto map(RecordEntity record) {
        if (record == null) return null;
        var dto = new RecordDto();
        dto.setId(record.getId());
        dto.setName(record.getName());
        dto.setSongs(record.getSongs().stream().map(Song::getName).collect(Collectors.toSet()));
        dto.setMusicians(record.getMusicians().stream().map(Musician::getName).toList());
        dto.setGenres(record.getGenres().stream().map(Genre::getName).toList());
        return dto;
    }

    public RecordEntity map(RecordDto dto) {
        var record = new RecordEntity();
        record.setId(dto.getId());
        record.setName(dto.getName());
        record.setSongs(dto.getSongs().stream().map(name->{

            var entity = new Song();
            entity.setName(name);
            return entity;
        }).toList());
        record.setGenres(dto.getGenres().stream().map(name->{
            var entity = new Genre();
            entity.setName(name);
            return entity;
        }).toList());
        record.setMusicians(dto.getMusicians().stream().map(name->{
            var entity = new Musician();
            entity.setName(name);
            return entity;
        }).collect(Collectors.toSet()));

        return record;
    }
}
