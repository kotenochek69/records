package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Модель данных производителя")
public class ProducerDto {
    @Schema(description = "Идентификатор производителя", example = "1")
    private Long id;

    @Schema(description = "Страна производителя", example = "USA")
    private String country;

    @Schema(description = "Имя производителя", example = "Apple")
    @NotBlank
    private String name;
}
