package com.example.recordsdb.controller;

import com.example.recordsdb.dto.CustomerDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/customers")
public class CustomerViewController {
    private final CustomerService customerService;
    private final RecordsCopyService recordsCopyService;

    @GetMapping
    public String viewCustomerPage(Model model) {
        model.addAttribute("customerlist", customerService.getAllCustomers());
        model.addAttribute("recordsCopies", recordsCopyService.getAllRecordsCopies());
        return "customers";
    }

    @DeleteMapping("/data/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return "redirect:data/customers"; // Перенаправляем пользователя обратно на страницу, где отображается список записей после удаления
    }

    @PostMapping("/createView")
    public String createCustomer(@ModelAttribute("customerDto") CustomerDto customerDto) {
        customerService.createCustomer(customerDto); // Сохранение песни
        return "redirect:/data/customers"; // Перенаправление на страницу со списком песен
    }
}
