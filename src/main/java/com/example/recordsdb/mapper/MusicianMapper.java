package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.model.Song;
import com.example.recordsdb.model.Genre;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MusicianMapper {
    public MusicianDto map(Musician musician) {
        if (musician == null) return null;
        var dto = new MusicianDto();
        dto.setId(musician.getId());
        dto.setName(musician.getName());
        dto.setCountry(musician.getCountry());
        dto.setSongs(musician.getSongs().stream().map(Song::getName).toList());
        dto.setGenres(musician.getGenres().stream().map(Genre::getName).toList());
        dto.setRecords(musician.getRecords().stream().map(RecordEntity::getName).toList());
        return dto;
    }

    public Musician map(MusicianDto dto) {
        var musician = new Musician();
        musician.setId(dto.getId());
        musician.setName(dto.getName());
        musician.setCountry(dto.getCountry());
        musician.setGenres(dto.getGenres().stream().map(name-> {
            var entity = new Genre();
            entity.setName(name);
            return entity;
        }).toList());
        musician.setRecords(dto.getRecords().stream().map(name-> {
            var entity = new RecordEntity();
            entity.setName(name);
            return entity;
        }).collect(Collectors.toSet()));
        musician.setSongs(dto.getSongs().stream().map(name-> {
            var entity = new Song();
            entity.setName(name);
            return entity;
        }).collect(Collectors.toSet()));


        return musician;
    }
}
