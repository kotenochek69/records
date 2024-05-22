package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Модель данных жанра")
public class GenreDto {

    @Schema(description = "Идентификатор жанра", example = "1")
    private Long id;

    @Schema(description = "Название жанра", example = "pop")
    @NotBlank
    private String name;

    @Schema(description = "Песни жанра")
    private List<String> songs = new ArrayList<>();

    @Schema(description = "Пластинки жанра")
    private List<String> records = new ArrayList<>();
}
