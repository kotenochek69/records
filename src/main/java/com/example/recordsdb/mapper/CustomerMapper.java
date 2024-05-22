package com.example.recordsdb.mapper;

import com.example.recordsdb.dto.CustomerDto;
import com.example.recordsdb.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private final RecordMapper mapper;

    public CustomerDto map(Customer customer) {
        if (customer == null) return null;
        var dto = new CustomerDto();
        if (customer.getId() != null) {
            dto.setId(customer.getId());
        }
        if (customer.getName() != null) {
            dto.setName(customer.getName());
        }
        if (customer.getAddress() != null) {
            dto.setAddress(customer.getAddress());
        }
        if (customer.getShipment() != null) {
            dto.setShipment(customer.getShipment());
        }
        if (customer.getAddress() != null) {
            dto.setAddress(customer.getAddress());
        }
        if (customer.getAmount() != null) {
            dto.setAmount(customer.getAmount());
        }
        if (customer.getRecordsCopies() != null) {
            dto.setRecordsNames(customer.getRecordsCopies().stream().map(r ->  {
                if (r.getRecord() != null) {
                    if (r.getRecord().getName() != null) {
                        return r.getRecord().getName();
                    }
                }
                return null;
            }
            ).toList());
        }
        return dto;
    }

    public Customer map(CustomerDto dto) {
        if (dto == null) return null;
        var customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setAmount(dto.getAmount());
        customer.setShipment(dto.getShipment());
        customer.setAddress(dto.getAddress());
        return customer;
    }
}
