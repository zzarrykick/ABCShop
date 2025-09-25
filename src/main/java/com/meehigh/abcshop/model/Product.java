package com.meehigh.abcshop.model;

import jakarta.persistence.*;
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

    private Category category;

    private String thumbnailUrl;

    private List<ProductType> productType;

    private BigDecimal price;

    private User author;
}
