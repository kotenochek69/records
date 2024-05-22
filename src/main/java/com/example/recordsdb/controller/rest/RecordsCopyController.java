package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.RecordsCopyDto;
import com.example.recordsdb.dto.SongDto;
import com.example.recordsdb.service.RecordsCopyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/data/records-copy")
@Schema(description = "Операции с копиями пластинок")
public class RecordsCopyController {
    private final RecordsCopyService recordsCopyService;

    @DeleteMapping("{id}")
    @Operation(description = "Удаление пластинки по ID", summary = "Удаляет пластинку по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешное удаление копии пластинки"),
            @ApiResponse(responseCode = "404", description = "Копия пластинки не найдена")
    })
    public void deleteById(@PathVariable @Schema(description = "Идентификатор копии пластнки") Long id) {
        recordsCopyService.deleteById(id);
    }

    @PutMapping("{id}")
    @Operation(description = "Редактирование пластинки", summary = "Редактирует параметры пластинки по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное редактирование копии пластинки"),
            @ApiResponse(responseCode = "404", description = "Копия пластинки не найдена")
    })
    public RecordsCopyDto updateRecordCopy(@PathVariable @Schema(description = "Идентификатор копии пластинки") Long id,
                                           @RequestParam(required = false) @Schema(description = "Год выпуска копии пластинки") @Min(1948) @Max(2024) Integer year,
                                           @RequestParam(required = false) @Schema(description = "Цена копии пластинки") @Min(0) Integer priceIn,
                                           @RequestParam(required = false) @Schema(description = "Идентификатор пластинки") Long recordId,
                                           @RequestParam(required = false) @Schema(description = "Идентификатор производителя копии пластинки") Long producerId) {
        return recordsCopyService.updateById(id, year, priceIn, recordId, producerId);
    }

    @PostMapping("/create")
    @Operation(description = "Создание копии пластинки", summary = "Создает новую копию пластинки с указанными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание копии пластинки"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    public RecordsCopyDto createRecordsCopyParams( @RequestParam @Schema(description = "Год выпуска копии пластинки") @Min(1948) @Max(2024) Integer year,
                                                   @RequestParam @Schema(description = "Цена копии пластинки") @Min(0) Integer priceIn,
                                                   @RequestParam @Schema(description = "Идентификатор пластинки") Long recordId,
                                                   @RequestParam @Schema(description = "Идентификатор производителя") Long producerId){
        return recordsCopyService.createRecordsCopy(year, priceIn, recordId, producerId);
    }

}
