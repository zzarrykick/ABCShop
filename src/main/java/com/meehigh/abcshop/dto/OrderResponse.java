package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

}
