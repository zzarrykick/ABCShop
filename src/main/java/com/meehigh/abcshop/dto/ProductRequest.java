package com.meehigh.abcshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductRequest {

    private String name;
    private String description;
    private CategoryResponse category;
    private String thumbnailUrl;
    private BigDecimal price;
    private Integer stock;
    private List<OrderLineResponse> orderLine;
}
