package com.example.recordsdb.controller;

import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.MusicianService;
import com.example.recordsdb.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("data/musicians")
public class MusicianViewController {

    private final MusicianService musicianService;
    private final RecordService recordService;

    @GetMapping
    public String viewMusicianPage(Model model) {
        model.addAttribute("musicianlist", musicianService.getAllMusicians());
        model.addAttribute("records", recordService.getAllRecords());
        return "musicians";
    }

    @DeleteMapping("/data/musicians/{id}")
    public String deleteMusician(@PathVariable Long id) {
        musicianService.deleteById(id);
        return "redirect:data/musicians"; // Перенаправляем пользователя обратно на страницу, где отображается список записей после удаления
    }
    @PostMapping("/createView")
    public String createSong(@ModelAttribute("musicianDto") MusicianDto musicianDto) {
        musicianService.createMusician(musicianDto); // Сохранение песни
        return "redirect:/data/songs"; // Перенаправление на страницу со списком песен
    }

}
