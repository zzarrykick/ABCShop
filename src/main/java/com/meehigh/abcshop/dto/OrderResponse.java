package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Status;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
