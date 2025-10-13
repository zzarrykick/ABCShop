package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.OrderLine;
import lombok.Data;

@Data
public class OrderLineResponse {
    private Integer quantity;
    private ProductResponse product;

    // conversie din entitate în DTO
    public static OrderLineResponse convertEntityToResponse(OrderLine orderLine) {
        OrderLineResponse orderLineResponse = new OrderLineResponse();

        // extragem datele
        orderLineResponse.setQuantity(orderLine.getQuantity());
        orderLineResponse.setProduct(ProductResponse.convertEntityToResponse(orderLine.getProduct()));
        return orderLineResponse;
    }
}
