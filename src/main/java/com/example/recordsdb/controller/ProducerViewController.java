package com.example.recordsdb.controller;

import com.example.recordsdb.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("data/producers")
public class ProducerViewController {

    private final ProducerService producerService;
    @GetMapping
    public String viewProducerPage(Model model) {
        model.addAttribute("producerlist", producerService.getAllProducers());
        return "producers";
    }
}
