package com.example.recordsdb;

import com.example.recordsdb.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RecordsDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordsDbApplication.class, args);
    }

}
