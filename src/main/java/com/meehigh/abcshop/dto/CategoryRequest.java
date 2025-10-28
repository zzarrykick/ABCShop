package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {

    @NotBlank
    private String name;

    private Category parent;
}
