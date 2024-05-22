package com.example.recordsdb.controller;

import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.GenreService;
import com.example.recordsdb.service.MusicianService;
import com.example.recordsdb.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/data/songs")
public class SongsViewController {
    private final SongService songService;
    private final GenreService genreService;

    private final MusicianService musicianService;
    @GetMapping
    public String viewSongPage(Model model) {
        model.addAttribute("musicianlist", musicianService.getAllMusicians());
        model.addAttribute("songlist", songService.getAllSongs());
        model.addAttribute("genrelist", genreService.getAllGenres());
        return "songs";
    }

    @DeleteMapping("/data/songs/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "redirect:data/songs"; // Перенаправляем пользователя обратно на страницу, где отображается список записей после удаления
    }

    @PostMapping("/createView")
    public String createSong(@ModelAttribute("songDto") SongDto songDto) {
        songService.createSong(songDto); // Сохранение песни
        return "redirect:/data/songs"; // Перенаправление на страницу со списком песен
    }
}
