package com.example.recordsdb.mapper;

import com.example.recordsdb.repository.RecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordsMapperTest {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RecordMapper mapper;


}
