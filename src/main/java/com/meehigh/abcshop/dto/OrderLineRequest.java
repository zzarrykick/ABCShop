package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderLineRequest {

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    private ProductResponse productName;

    private OrderResponse order;

}
