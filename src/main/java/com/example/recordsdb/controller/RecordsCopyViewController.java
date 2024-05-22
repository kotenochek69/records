package com.example.recordsdb.controller;

import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.CustomerService;
import com.example.recordsdb.service.ProducerService;
import com.example.recordsdb.service.RecordService;
import com.example.recordsdb.service.RecordsCopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("data/records-copy")
public class RecordsCopyViewController {
    private final RecordsCopyService recordsCopyService;

    private final ProducerService producerService;
    private final RecordService recordService;

    @GetMapping
    public String viewRecordsCopyPage(Model model) {
        model.addAttribute("recordsCopylist", recordsCopyService.getAllRecordsCopy());
        model.addAttribute("producers", producerService.getAllProducers());
        model.addAttribute("records", recordService.getAllRecords());
        return "recordsCopy";
    }

    @PostMapping("/createView")
    public String createRecordsCopy(@ModelAttribute("recordsCopyDto") RecordsCopyDto recordsCopyDto) {
        recordsCopyService.createRecordsCopy(recordsCopyDto); // Сохранение песни
        return "redirect:/data/records-copy"; // Перенаправление на страницу со списком песен
    }

    @DeleteMapping("/data/records-copy/{id}")
    public String deleteRecord(@PathVariable Long id) {
        recordsCopyService.deleteRecord(id);
        return "redirect:data/records-copy"; // Перенаправляем пользователя обратно на страницу, где отображается список записей после удаления
    }
}
