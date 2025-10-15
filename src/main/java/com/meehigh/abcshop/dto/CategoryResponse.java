package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Category;
import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;
    private String name;
    private Category parent;



}
