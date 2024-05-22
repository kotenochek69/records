package com.example.recordsdb.service;

import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.mapper.RecordsCopyMapper;
import com.example.recordsdb.model.*;
import com.example.recordsdb.repository.ProducerRepository;
import com.example.recordsdb.repository.RecordRepository;
import com.example.recordsdb.repository.RecordsCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordsCopyService {
    private final RecordRepository recordRepository;
    private final RecordsCopyRepository repo;
    private final ProducerRepository producerRepository;
    private final RecordsCopyMapper mapper;


    public List<RecordsCopyDto> getAllRecordsCopy() {
        return repo.findAll(Sort.by("id")).stream().map(mapper::map).toList();
    }


    public RecordsCopyDto createRecordsCopy(Integer year, Integer price_in, Long recordId, Long producerId) {
        var recordsCopy = new RecordsCopy();
        recordsCopy.setYear(year);
        recordsCopy.setPrice_in(price_in);
        var record = recordRepository.findById(recordId).orElseThrow();
        recordsCopy.setRecord(record);
        var producer = producerRepository.findById(producerId).orElseThrow();
        recordsCopy.setProducer(producer);
        return mapper.map(repo.save(recordsCopy));
    }

    public RecordsCopyDto createRecordsCopy(RecordsCopyDto recordsCopyDto) {
        var recordsCopy = new RecordsCopy();
        recordsCopy.setPrice_in(recordsCopyDto.getPrice_in());
        recordsCopy.setYear(recordsCopyDto.getYear());

        // Поиск музыканта по имени
        var record = recordRepository.findByName(recordsCopyDto.getRecord()).orElseThrow(() -> new IllegalArgumentException("Invalid record name"));

        // Поиск жанра по имени
        var producer = producerRepository.findByName(recordsCopyDto.getProducer()).orElseThrow(() -> new IllegalArgumentException("Invalid producer name"));

        recordsCopy.setRecord(record);
        recordsCopy.setProducer(producer);

        // Сохранение песни в базе данных
        return mapper.map(repo.save(recordsCopy));
    }
    
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    public RecordsCopyDto updateById(Long id, Integer year, Integer priceIn, Long recordId, Long producerId) {
        var copy = repo.findById(id).orElseThrow();
        if (year != null){
            copy.setYear(year);
        }
        if (priceIn != null){
            copy.setPrice_in(priceIn);
        }
        if (recordId != null){
            var record = recordRepository.findById(recordId).orElseThrow();
            copy.setRecord(record);
        }
        if (producerId != null){
            var producer = producerRepository.findById(producerId).orElseThrow();
            copy.setProducer(producer);
        }

        return mapper.map(repo.save(copy));
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

    public List<RecordsCopyDto> getAllRecordsCopies() {
        return repo.findAll().stream().map(mapper::map).toList();
    }
}
