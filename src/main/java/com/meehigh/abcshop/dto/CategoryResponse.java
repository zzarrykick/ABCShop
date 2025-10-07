package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import lombok.Data;

@Data
public class CategoryResponse {

    private Long id;
    private String name;
    private Category parent;

    // conversie din entitate în DTO
    public static CategoryResponse convertEntityToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        // extragem datele

        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setParent(category.getParent());

        return categoryResponse;
    }

}
