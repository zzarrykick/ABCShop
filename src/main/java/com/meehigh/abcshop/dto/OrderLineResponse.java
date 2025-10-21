package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meehigh.abcshop.model.OrderLine;
import lombok.Data;

@Data
public class OrderLineResponse {

    private Integer quantity;
    private ProductResponse product;

    @JsonIgnore
    private OrderResponse order;


}
