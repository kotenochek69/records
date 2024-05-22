package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.ProducerDto;
import com.example.recordsdb.model.RecordEntity;
import com.example.recordsdb.model.Producer;
import org.springframework.stereotype.Component;

@Component
public class ProducerMapper {
    public ProducerDto map(Producer producer) {
        if (producer == null) return null;
        var dto = new ProducerDto();
        dto.setId(producer.getId());
        dto.setName(producer.getName());
        dto.setCountry(producer.getCountry());
        return dto;
    }

    public Producer map(ProducerDto dto) {
        var producer = new Producer();
        producer.setId(dto.getId());
        producer.setName(dto.getName());
        producer.setCountry(dto.getCountry());
        return producer;
    }
}
