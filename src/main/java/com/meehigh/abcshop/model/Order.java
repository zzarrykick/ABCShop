package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private BigDecimal totalPrice;

/*    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address deliveryAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address userAddress;*/

    private LocalDateTime orderDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Map<Product, Integer> products;

    @Enumerated(EnumType.STRING)
    private Status status;
}
