package com.meehigh.abcshop.dto;


import com.meehigh.abcshop.model.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {

    private Long userId;
    private Long deliveryAddressId;
    private Long userAddressId;

    @NotNull
    private LocalDateTime orderDate;

    private List<OrderLineResponse> orderLines = new ArrayList<>();
    private Status status;

}
