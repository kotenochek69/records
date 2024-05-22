package com.example.recordsdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("data/home")
@Schema(description = "Операции с данными")
public class DataController {

    @GetMapping
    @Operation(description = "Показ главной страницы", summary = "Показывает главную страницу")
    public String viewHomePage(Model model) {
        model.addAttribute("home");
        return "home";
    }

}
