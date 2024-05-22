package com.example.recordsdb.service;

import com.example.recordsdb.dto.CustomerDto;
import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.mapper.CustomerMapper;
import com.example.recordsdb.model.*;
import com.example.recordsdb.repository.CustomerRepository;
import com.example.recordsdb.repository.RecordsCopyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;
    private final RecordsCopyRepository recordsCopyRepository;
    private final CustomerMapper mapper;

    public List<CustomerDto> getAllCustomers() {
        return repo.findAll().stream().map(mapper::map).toList();
    }

    @Transactional
    public CustomerDto bindRecord(Long customerId, Long recordsCopyId) {
        System.out.printf("Customer"+customerId+"Record"+recordsCopyId);
        Customer customer = repo.findById(customerId).orElseThrow();
        RecordsCopy record = recordsCopyRepository.findById(recordsCopyId).orElseThrow();
        record.setCustomer(customer);
        customer.getRecordsCopies().add(record);
        recordsCopyRepository.save(record);
        return mapper.map(repo.save(customer));
    }

    public CustomerDto createCustomer(String name, String address, Boolean shipment, Integer amount) {
        var customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setShipment(shipment);
        customer.setAmount(amount);

        return mapper.map(repo.save(customer));
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setShipment(customerDto.getShipment());
        customer.setAmount(customerDto.getAmount());
        return mapper.map(repo.save(customer));
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
