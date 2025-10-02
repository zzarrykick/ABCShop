package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.OrderRequest;
import com.meehigh.abcshop.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Data
public class OrderRequestResponse {
    private String name;

    private BigDecimal totalPrice;

    private Address deliveryAddress;

    private LocalDateTime orderDate;

    private Status status;

    //TODO
    public static OrderRequestResponse convertEntityToResponse(OrderRequest orderRequest) {
        OrderRequestResponse orderRequestResponse = new OrderRequestResponse();
        OrderRequestResponse.setName(orderRequest.getUser());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRoles(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
        return userResponse;
    }
}
