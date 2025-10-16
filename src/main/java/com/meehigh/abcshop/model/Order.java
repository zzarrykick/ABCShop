package com.meehigh.abcshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "order_list")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonIgnore // Ignorăm pentru JSON — previne recursivitate și concurrent modification
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

   @OneToOne(fetch = FetchType.EAGER)
    private Address deliveryAddress;

    @OneToOne(fetch = FetchType.EAGER)
    private Address userAddress;

    @NotNull
    @NotBlank(message = "Order date cannot be blank")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;
}
