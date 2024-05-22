package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.MusicianDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.MusicianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("data/musicians")
@Schema(description = "Операции с музыкантами")
public class MusicianController {
    private final MusicianService musicianService;

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное выполнение запроса"),
            @ApiResponse(responseCode = "404", description = "Музыкант не найден")
    })
    @Operation(description = "Привязание пластинки по ID", summary = "Привязывает пластинку по указанному идентификатору")
    public MusicianDto bindRecord(@PathVariable(name ="id") @Schema(description = "Идентификатор музыканта") Long musicianId,
                                  @RequestParam(name = "record")  @Schema(description = "Идентификатор пластинки") Long recordId) {
        return musicianService.bindRecord(musicianId, recordId);
    }

    @DeleteMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное удаление музыканта"),
            @ApiResponse(responseCode = "404", description = "Музыкант не найден")
    })
    @Operation(description = "Удаление музыканта по ID", summary = "Удаляет музыканта по указанному идентификатору")
    public void deleteById(@PathVariable @Schema(description = "Идентификатор музыканта") Long id) {
        musicianService.deleteById(id);
    }


    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание"),
            @ApiResponse(responseCode = "404", description = "Музыкант не найден")
    })
    @Operation(description = "Создание музыканта", summary = "Создает нового музыканта с указанными параметрами")
    public MusicianDto createMusicianParams(@RequestParam @Schema(description = "Имя музыканта") String name,
                                            @RequestParam @Schema(description = "Страна музыканта") String country){
        return musicianService.createMusician(name, country);
    }

}

