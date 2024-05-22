package com.example.recordsdb.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Модель данных клиента")
public class CustomerDto {

    @Schema(description = "Идентификатор клиента", example = "1")
    Long id;

    @Schema(description = "Имя клиента", example = "Иван")
    @NotBlank
    String name;

    @Schema(description = "Адрес клиента", example = "ул. Ленина, д. 10")
    String address;

    @Schema(description = "Статус доставки клиента", example = "true")
    Boolean shipment;

    @Min(0)
    @Schema(description = "Сумма покупки клиента", example = "100")
    Integer amount;

    @Schema(description = "Список названий пластинок клиента", example = "[\"Гимн России\",\"Розовое вино\"]")
    @NotBlank
    List<String> recordsNames = new ArrayList<>();
}
