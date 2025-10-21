package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private CategoryResponse category;
    private String thumbnailUrl;
    private BigDecimal price;
    private Integer stock;
}
