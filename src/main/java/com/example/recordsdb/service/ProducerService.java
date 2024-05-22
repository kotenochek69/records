package com.example.recordsdb.service;

import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.dto.ProducerDto;
import com.example.recordsdb.mapper.GenreMapper;
import com.example.recordsdb.mapper.ProducerMapper;
import com.example.recordsdb.model.Musician;
import com.example.recordsdb.model.Producer;
import com.example.recordsdb.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository repo;
    private final ProducerMapper mapper;


    public ProducerDto createProducer(String name, String country) {
        var producer = new Producer();
        producer.setName(name);
        producer.setCountry(country);
        return mapper.map(repo.save(producer));
    }

    public List<ProducerDto> getAllProducers() {
        return repo.findAll().stream().map(mapper::map).toList();
    }

}
