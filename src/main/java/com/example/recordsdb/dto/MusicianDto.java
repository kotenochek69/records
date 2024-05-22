package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Schema(description = "Модель данных музыканта")
public class MusicianDto {

    @Schema(description = "Идентификатор музыканта", example = "1")
    private Long id;

    @Schema(description = "Имя музыканта", example = "Иван")
    @NotBlank
    private String name;

    @Schema(description = "Страна музыканта", example = "Россия")
    private String country;

    @Schema(description = "Список песен музыканта")
    private List<String> songs = new ArrayList<>();

    @Schema(description = "Список жанров музыканта")
    private List<String> genres = new ArrayList<>();

    @Schema(description = "Список пластинок музыканта")
    private List<String> records = new ArrayList<>();
}
