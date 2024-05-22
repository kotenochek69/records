package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/data/songs")
@Schema(description = "Операции с песнями")
public class SongsController {

    private final SongService songService;

    @DeleteMapping("{id}")
    @Operation(description = "Удаление песни по ID", summary = "Удаляет песню по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешное удаление песни"),
            @ApiResponse(responseCode = "404", description = "Песня не найдена")
    })
    public void deleteById(@PathVariable @Schema(description = "Идентификатор песни") Long id) {
        songService.deleteById(id);
    }

    @PostMapping("/create")
    @Operation(description = "Создание песни", summary = "Создает новую песню с указанными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание песни"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    public SongDto createSongParams(@RequestParam @Schema(description = "Имя песни") String name,
                                    @RequestParam @Schema(description = "Год выпуска песни") @Min(1948) @Max(2024) Integer year,
                                    @RequestParam @Schema(description = "Идентификатор музыканта") Long musicianId,
                                    @RequestParam @Schema(description = "Идентификатор жанра") Long genreId){
        return songService.createSong(name, year, musicianId, genreId);
    }
}
