package com.example.recordsdb.service;

import com.example.recordsdb.dto.RecordDto;
import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.mapper.RecordMapper;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.RecordsCopy;
import com.example.recordsdb.repository.GenreRepository;
import com.example.recordsdb.repository.MusicianRepository;
import com.example.recordsdb.repository.RecordRepository;
import com.example.recordsdb.repository.RecordsCopyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository repo;
    private final MusicianRepository musicianRepository;
    private final GenreRepository genreRepository;
    private final RecordsCopyRepository recordsCopyRepository;
    private final RecordMapper mapper;

    public Set<RecordDto> getAllRecords() {
        return repo.findAll().stream().map(mapper::map).collect(Collectors.toSet());
    }

    public RecordDto createRecord(String name, Long musicianId, Long genreId) {
        var record = new RecordEntity();
        record.setName(name);
        var musicians = musicianRepository.findById(musicianId).orElseThrow();
        var genres = genreRepository.findById(genreId).orElseThrow();
        record.getMusicians().add(musicians);
        record.getGenres().add(genres);
        return mapper.map(repo.save(record));
    }

    @Transactional
    public void deleteById(Long id) {
        var record = repo.findById(id).orElseThrow();
        recordsCopyRepository.deleteAll(record.getRecordsCopy());
        repo.deleteById(id);
    }
    public int deleteRecord(Long id) {
        try {
            repo.deleteById(id);
            return 200; // Возвращаем статус 200 OK в случае успешного удаления
        } catch (Exception e) {
            e.printStackTrace();
            return 500; // Возвращаем статус 500 Internal Server Error в случае ошибки
        }
    }
}
