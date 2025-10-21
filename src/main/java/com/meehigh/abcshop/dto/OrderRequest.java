package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meehigh.abcshop.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {

    private UserResponse user;
    private AddressResponse deliveryAddress;
    private AddressResponse userAddress;

    @NotNull
    private LocalDateTime orderDate;

    private List<OrderLineResponse> orderLines = new ArrayList<>();
    private Status status;

}
