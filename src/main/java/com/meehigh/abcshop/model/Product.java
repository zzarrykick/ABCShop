package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    private String thumbnailUrl;

    private BigDecimal price;

    private Integer stock;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<OrderLine> orderLine;
}
