package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Модель данных копии пластинки")
public class RecordsCopyDto {

    @Schema(description = "Идентификатор копии пластинки", example = "1")
    private Long id;

    @Min(0)
    @Schema(description = "Цена покупки пластинки", example = "100")
    private Integer price_in;

    @Min(0)
    @Schema(description = "Цена продажи пластинки", example = "1000")
    private Integer price_out;

    @Min(1948)
    @Max(2024)
    @Schema(description = "Год выпуска пластинки", example = "1999")
    private Integer year;

    @Schema(description = "Поставщик пластинки", example = "RecStore")
    @NotBlank
    private String supplier;

    @Min(1)
    @Max(3)
    @Schema(description = "Редкость пластинки", example = "1")
    private Integer rarity;

    @Schema(description = "Статус продажи пластинки", example = "1")
    private Boolean sold;

    @Schema(description = "Производитель пластинки")
    private ProducerDto producer;

    @Schema(description = "Вид пластинки")
    private RecordDto record;

    @Schema(description = "Покупатель пластинки")
    private CustomerDto customer;

}
