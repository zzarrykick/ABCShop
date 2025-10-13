package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderResponse {

    private UserResponse user;
    private BigDecimal totalPrice;
    private AddressResponse deliveryAddress;
    private AddressResponse userAddress;
    private LocalDateTime orderDate;
    private List<OrderLineResponse> orderLines = new ArrayList<>();
    private Status status;

    // conversie din entitate în DTO
    public static OrderResponse convertEntityToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        // extragem datele
        orderResponse.setUser(UserResponse.convertEntityToResponse(order.getUser()));
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setDeliveryAddress(AddressResponse.convertEntityToResponse(order.getDeliveryAddress()));
        orderResponse.setUserAddress(AddressResponse.convertEntityToResponse(order.getUserAddress()));
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setOrderLines(
                order.getOrderLines().stream()
                        .map(orderLine -> OrderLineResponse.convertEntityToResponse(orderLine))
                        .collect(Collectors.toList())
        );
        orderResponse.setStatus(order.getStatus());

        return orderResponse;
    }
}
