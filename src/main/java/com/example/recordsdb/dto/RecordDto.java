package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Schema(description = "Модель данных пластинки")
public class RecordDto {
    @Schema(description = "Идентификатор пластинки", example = "1")
    private Long id;

    @Schema(description = "Имя пластинки", example = "1")
    @NotBlank
    private String name;

    @Schema(description = "Копии пластинки")
    private List<String> recordsCopy = new ArrayList<>();

    @Schema(description = "Жанры пластинки")
    private List<String> genres = new ArrayList<>();

    @Schema(description = "Песни пластинки")
    private Set<String> songs = new HashSet<>();

    @Schema(description = "Музыканты пластинки")
    private List<String> musicians = new ArrayList<>();
}
