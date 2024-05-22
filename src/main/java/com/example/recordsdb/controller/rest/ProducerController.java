package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.ProducerDto;
import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.service.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/data/producers")
@Schema(description = "Операции с производителями")
public class ProducerController {
    private final ProducerService producerService;

    @PostMapping("/create")
    @Operation(description = "Создание производителя", summary = "Создает нового производителя с указанными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание производителя"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    public ProducerDto createProducerParams(@RequestParam @Schema(description = "Имя производителя") String name,
                                            @RequestParam @Schema(description = "Название страны") String country){
        return producerService.createProducer(name, country);
    }
}
