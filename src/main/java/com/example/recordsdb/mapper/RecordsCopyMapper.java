package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.model.RecordsCopy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecordsCopyMapper {
    private final RecordMapper recordMapper;
    private final CustomerMapper customerMapper;
    private final ProducerMapper producerMapper;

    public RecordsCopyDto map(RecordsCopy recordsCopy) {
        if (recordsCopy == null){
            return null;
        }
        var dto = new RecordsCopyDto();
        dto.setId(recordsCopy.getId());
        dto.setYear(recordsCopy.getYear());
        dto.setRecord(recordMapper.map(recordsCopy.getRecord()));
        dto.setCustomer(customerMapper.map(recordsCopy.getCustomer()));
        dto.setProducer(producerMapper.map(recordsCopy.getProducer()));
        dto.setPrice_in(recordsCopy.getPrice_in());
        dto.setPrice_out(recordsCopy.getPrice_out());
        return dto;
    }

    public RecordsCopy map(RecordsCopyDto dto) {
        var recordsCopy = new RecordsCopy();
        recordsCopy.setId(dto.getId());
        recordsCopy.setYear(dto.getYear());
        recordsCopy.setCustomer(customerMapper.map(dto.getCustomer()));
        recordsCopy.setRecord(recordMapper.map(dto.getRecord()));
        return recordsCopy;
    }
}
