package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "Модель данных песни")
public class SongDto {

    @Schema(description = "Идентификатор песни", example = "1")
    private Long id;

    @Min(0)
    @Max(2024)
    @Schema(description = "Год песни", example = "1975")
    private Integer year;

    @Schema(description = "Имя песни", example = "Imagine")
    @NotBlank
    private String name;

    @Schema(description = "Музыканты песни")
    private String musician;

    @Schema(description = "Жанры песни")
    private String genre;

    @Schema(description = "Пластинки песни")
    private Set<String> records = new HashSet<>();

}
