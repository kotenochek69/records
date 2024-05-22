package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.RecordDto;
import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/data/records")
@Schema(description = "Операции с пластинками")
public class RecordController {
    private final RecordService recordService;
    @GetMapping
    @Operation(description = "Показ списка всех пластинок", summary = "Показывает список из всех пластинок")
    public Set<RecordDto> getAllRecords() {
        return recordService.getAllRecords();
    }

    @PostMapping("/create")
    @Operation(description = "Создание пластинки", summary = "Создает новую пластинку с указанными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание пластинки"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    public RecordDto createRecordParams(@RequestParam @Schema(description = "Имя пластинки") String name,
                                        @RequestParam @Schema(description = "Идентификатор музыканта") Long musicianId,
                                        @RequestParam @Schema(description = "Идентификатор жанра") Long genreId){
        return recordService.createRecord(name, musicianId, genreId);
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление пластинки по ID", summary = "Удаляет пластинку по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешное удаление пластинки"),
            @ApiResponse(responseCode = "404", description = "Пластинка не найдена")
    })
    public void deleteById(@PathVariable @Schema(description = "Идентификатор пластинки") Long id) {
        recordService.deleteById(id);
    }
}
