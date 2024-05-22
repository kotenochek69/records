package com.example.recordsdb.service;

import com.example.recordsdb.AbstractTest;
import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.repository.MusicianRepository;
import com.example.recordsdb.repository.RecordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MusicianServiceTest extends AbstractTest {

    @Autowired
    private MusicianService service;

    @Autowired
    private MusicianRepository repository;

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void createMusicianSuccess() {
        String name = "John Doe";
        String country = "USA";

        MusicianDto createdMusicianDto = service.createMusician(name, country);
        Musician createdMusician = repository.findById(createdMusicianDto.getId()).orElse(null);

        Assertions.assertThat(createdMusician).isNotNull();

        Assertions.assertThat(createdMusician.getName()).isEqualTo(name);
        Assertions.assertThat(createdMusician.getCountry()).isEqualTo(country);
    }

    @Test
    void deleteMusicianSuccess() {
        Musician musician = new Musician();
        musician.setName("John Doe");
        musician.setCountry("USA");
        Musician savedMusician = repository.save(musician);

        Optional<Musician> foundMusicianOptional = repository.findById(savedMusician.getId());
        assertTrue(foundMusicianOptional.isPresent());

        service.deleteById(savedMusician.getId());

        Optional<Musician> deletedMusicianOptional = repository.findById(savedMusician.getId());
        assertFalse(deletedMusicianOptional.isPresent());
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void getAllMusiciansSuccess() {
        Musician musician1 = new Musician();
        musician1.setName("Alice");
        musician1.setCountry("USA");
        Musician savedMusician1 = repository.save(musician1);

        Musician musician2 = new Musician();
        musician2.setName("Bob");
        musician1.setCountry("England");
        Musician savedMusician2 = repository.save(musician2);

        List<MusicianDto> allMusicians = service.getAllMusicians();

        Assertions.assertThat(allMusicians).extracting(MusicianDto::getId).containsExactlyInAnyOrder(savedMusician1.getId(), savedMusician2.getId());
        Assertions.assertThat(allMusicians).extracting(MusicianDto::getName).containsExactlyInAnyOrder("Alice", "Bob");
    }
    
}