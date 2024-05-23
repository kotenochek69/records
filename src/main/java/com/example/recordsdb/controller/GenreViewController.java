package com.example.recordsdb.controller;

import com.example.recordsdb.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("data/genres")
@Schema(description = "Операции с жанрами")
public class GenreViewController {
    private final GenreService genreService;

    @GetMapping
    @Operation(description = "Показ списка всех жанров", summary = "Показывает список из всех жанров")
    public String viewGenrePage(Model model) {
        model.addAttribute("genrelist", genreService.getAllGenres());
        return "genres";
    }
}
