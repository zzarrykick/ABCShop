package com.meehigh.abcshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private BigDecimal totalPrice;
    private Address deliveryAddress;
    private Address userAddress;
    private LocalDateTime orderDate;
    private List<OrderLine> orderLines;
    private Enum<Status> status;
}
