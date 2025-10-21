package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.OrderLine;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    private CategoryResponse category;

    //@NotBlank(message = "Thumbnail URL cannot be blank")
    @URL(message = "Thumbnail URL must be a valid URL")
    private String thumbnailUrl;

    @Min(value = 1, message = "Price must be greater than 0 ")
    private BigDecimal price;

    private Integer stock;

    private List<OrderLineResponse> orderLine;

}
