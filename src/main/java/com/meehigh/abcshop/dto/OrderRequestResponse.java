package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.OrderRequest;
import com.meehigh.abcshop.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderRequestResponse {
    private String name;

    private BigDecimal totalPrice;

    private Address deliveryAddress;

    private LocalDateTime orderDate;

    private Status status;



    // conversie din entitate în DTO
    public static OrderRequestResponse convertEntityToResponse(OrderRequest orderRequest) {
        OrderRequestResponse orderRequestResponse = new OrderRequestResponse();

        // extragem datele
        orderRequestResponse.setName(orderRequest.getUser().getFirstName() + " " + orderRequest.getUser().getLastName());
        orderRequestResponse.setTotalPrice(orderRequest.getTotalPrice());
        orderRequestResponse.setDeliveryAddress(orderRequest.getDeliveryAddress());
        orderRequestResponse.setOrderDate(orderRequest.getOrderDate());
        orderRequestResponse.setStatus(orderRequest.getStatus());

        return orderRequestResponse;
    }
}
