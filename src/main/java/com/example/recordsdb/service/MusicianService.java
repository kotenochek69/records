package com.example.recordsdb.service;

import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.mapper.MusicianMapper;
import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.model.Genre;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Song;
import com.example.recordsdb.repository.MusicianRepository;
import com.example.recordsdb.repository.RecordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.recordsdb.model.Musician;

@Service
@RequiredArgsConstructor
public class MusicianService {

    private final MusicianRepository musicianRepo;
    private final RecordRepository recordRepo;
    private final MusicianMapper mapper;

    @Transactional
    public List<MusicianDto> getAllMusicians() {
        return musicianRepo.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public MusicianDto bindRecord(Long musicianId, Long recordId) {
        Musician musician = musicianRepo.findById(musicianId).orElseThrow();
        RecordEntity record = recordRepo.findById(recordId).orElseThrow();
        musician.getRecords().add(record);
        return mapper.map(musicianRepo.save(musician));
    }

    public MusicianDto createMusician(String name, String country) {
        var musician = new Musician();
        musician.setName(name);
        musician.setCountry(country);
        return mapper.map(musicianRepo.save(musician));
    }

    public MusicianDto createMusician(MusicianDto musicianDto) {
        var musician = new Musician();
        musician.setName(musicianDto.getName());
        musician.setCountry(musicianDto.getCountry());
        return mapper.map(musicianRepo.save(musician));
    }

    @Transactional
    public void deleteById(Long id) {
        var musician = musicianRepo.findById(id).orElseThrow();
        recordRepo.deleteAll(musician.getRecords());
        musicianRepo.deleteById(id);
    }

}
