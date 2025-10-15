package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.OrderLine;
import lombok.Data;

@Data
public class OrderLineResponse {
    private Integer quantity;
    private ProductResponse product;


}
