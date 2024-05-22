package com.example.recordsdb.controller;

import com.example.recordsdb.dto.RecordDto;
import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.service.GenreService;
import com.example.recordsdb.service.MusicianService;
import com.example.recordsdb.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("data/records")
public class RecordsViewController {
    private final RecordService recordService;
    private final GenreService genreService;
    private final MusicianService musicianService;




    @GetMapping
    public String viewRecordPage(Model model) {
        model.addAttribute("recordlist", recordService.getAllRecords());
        model.addAttribute("musicianlist", musicianService.getAllMusicians());
        model.addAttribute("genrelist", genreService.getAllGenres());
        return "records";
    }
    @DeleteMapping("/data/records/{id}")
    public String deleteRecord(@PathVariable Long id) {
        recordService.deleteById(id);
        return "redirect:data/records"; // Перенаправляем пользователя обратно на страницу, где отображается список записей после удаления
    }


}
