package com.meehigh.abcshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderLineRequest {

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Product cannot be null")
    private Long productId;

    private OrderResponse order;

}
