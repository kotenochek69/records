package com.example.recordsdb.service;

import com.example.recordsdb.AbstractTest;
import com.example.recordsdb.dto.CustomerDto;
import com.example.recordsdb.model.Customer;
import com.example.recordsdb.model.RecordsCopy;
import com.example.recordsdb.repository.CustomerRepository;
import com.example.recordsdb.repository.RecordsCopyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest extends AbstractTest {
    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private RecordsCopyRepository copyRepository;

    @Test
    void bindRecordSuccess() {
        Customer customer = new Customer();
        customer.setName("Allah");
        customer.setAmount(100);
        var savedCustomer = repository.save(customer);

        RecordsCopy record = new RecordsCopy();
        record.setYear(1999);
        var savedRecord = copyRepository.save(record);

        service.bindRecord(savedCustomer.getId(), savedRecord.getId());

        var receivedCustomer = repository.findById(savedCustomer.getId()).orElseThrow();
        var receivedCopy = copyRepository.findById(savedRecord.getId()).orElseThrow();
        Assertions.assertThat(receivedCopy.getCustomer().getId()).isEqualTo(customer.getId());
        Assertions.assertThat(receivedCustomer.getId()).isEqualTo(receivedCopy.getCustomer().getId());
    }

    @Test
    void createCustomerSuccess() {
        String name = "John Doe";
        String address = "123 Main Street";
        boolean shipment = true;
        int amount = 1000;

        CustomerDto createdCustomerDto = service.createCustomer(name, address, shipment, amount);
        Customer createdCustomer = repository.findById(createdCustomerDto.getId()).orElse(null);

        Assertions.assertThat(createdCustomer).isNotNull();

        Assertions.assertThat(createdCustomer.getName()).isEqualTo(name);
        Assertions.assertThat(createdCustomer.getAddress()).isEqualTo(address);
        Assertions.assertThat(createdCustomer.getShipment()).isEqualTo(shipment);
        Assertions.assertThat(createdCustomer.getAmount()).isEqualTo(amount);
    }

    @Test
    void deleteCustomerSuccess() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setAddress("123 Main Street");
        customer.setShipment(true);
        customer.setAmount(1000);
        Customer savedCustomer = repository.save(customer);

        Optional<Customer> foundCustomerOptional = repository.findById(savedCustomer.getId());
        assertTrue(foundCustomerOptional.isPresent());

        service.deleteById(savedCustomer.getId());

        Optional<Customer> deletedCustomerOptional = repository.findById(savedCustomer.getId());
        assertFalse(deletedCustomerOptional.isPresent());
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void getAllCustomersSuccess() {
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setAddress("123 Main Street");
        customer1.setShipment(true);
        customer1.setAmount(1000);
        Customer savedCustomer1 = repository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setAddress("456 Elm Street");
        customer2.setShipment(false);
        customer2.setAmount(2000);
        Customer savedCustomer2 = repository.save(customer2);

        List<CustomerDto> allCustomers = service.getAllCustomers();

        Assertions.assertThat(allCustomers).extracting(CustomerDto::getId).containsExactlyInAnyOrder(savedCustomer1.getId(), savedCustomer2.getId());
        Assertions.assertThat(allCustomers).extracting(CustomerDto::getName).containsExactlyInAnyOrder("Alice", "Bob");
        Assertions.assertThat(allCustomers).extracting(CustomerDto::getAddress).containsExactlyInAnyOrder("123 Main Street", "456 Elm Street");
        Assertions.assertThat(allCustomers).extracting(CustomerDto::getShipment).containsExactlyInAnyOrder(true, false);
        Assertions.assertThat(allCustomers).extracting(CustomerDto::getAmount).containsExactlyInAnyOrder(1000, 2000);
    }

}