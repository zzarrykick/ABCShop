package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Category category;
    private String thumbnailUrl;
    private BigDecimal price;
    private Integer stock;
    private List<OrderLine> orderLine;

    // conversie din entitate în DTO
    public static ProductResponse convertEntityToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();

        // extragem datele

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(product.getCategory());
        productResponse.setThumbnailUrl(product.getThumbnailUrl());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());
        productResponse.setOrderLine(product.getOrderLine());

        return productResponse;
    }

}
