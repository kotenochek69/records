package com.example.recordsdb.controller.rest;

import com.example.recordsdb.dto.CustomerDto;
import com.example.recordsdb.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("data/customers")
@Schema(description = "Операции с клиентами")
public class CustomerController {
    private final CustomerService customerService;


    @DeleteMapping("{id}")
    @Operation(description = "Удаление клиента по ID", summary = "Удаляет клиента по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешное удаление клиента"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    public void deleteById(@PathVariable @Schema(description = "Идентификатор покупателя") Long id) {
        customerService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Получение информации о клиенте", summary = "Получает информацию о клиенте по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение информации о клиенте"),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    public CustomerDto bindRecord(@PathVariable(name ="id") @Schema(description = "Идентификатор покупателя") Long customerId,
                                  @RequestParam(name = "recordsCopy") @Schema(description = "Идентификатор копии пластинки") Long recordsCopyId) {
        return customerService.bindRecord(customerId, recordsCopyId);
    }

    @PostMapping("/create")
    @Operation(description = "Создание клиента", summary = "Создает нового клиента с указанными параметрами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное создание клиента"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса")
    })
    public CustomerDto createCustomerParams(@RequestParam @Schema(description = "Имя покупателя") String name,
                                            @RequestParam @Schema(description = "Адрес покупателя") String address,
                                            @RequestParam(required = false, defaultValue = "false") @Schema(description = "Статус доставки покупателя") Boolean shipment,
                                            @RequestParam @Schema(description = "Сумма покупок покупателя") @Min(0) Integer amount) {
        return customerService.createCustomer(name, address, shipment, amount);
    }

}
