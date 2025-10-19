package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.dto.CategoryRequest;
import com.meehigh.abcshop.dto.CategoryResponse;
import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.exception.CategoryNotFoundException;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.repository.CategoryRepository;
import com.meehigh.abcshop.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> Utils.categoryEntityToResponse(category))
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map( category ->  Utils.categoryEntityToResponse(category))
                .orElseThrow(() -> new AddressNotFoundException("Category with id: " + id + " not found"));
    }

    public List<CategoryResponse> getCategoryByName(String categoryName) {
        List<CategoryResponse> categoryResponses = categoryRepository.findByName(categoryName).stream()
                .map(category -> Utils.categoryEntityToResponse(category)).collect(Collectors.toList());
        if (categoryResponses.isEmpty()) {
            throw new CategoryNotFoundException("Category with name: " + categoryName + " not found");
        }

        return categoryResponses;
    }

    @Transactional
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        return Utils.categoryEntityToResponse(categoryRepository.save(Utils.categoryRequestToEntity(categoryRequest)));
    }

    @Transactional
    public CategoryResponse editCategory(Long id, CategoryRequest categoryRequest) {
        Category category = Utils.categoryRequestToEntity(categoryRequest);
        return categoryRepository.findById(id).map(cat -> {
            category.setId(cat.getId());
            categoryRepository.save(category);
            return Utils.categoryEntityToResponse(category);
        }).orElseThrow(() -> new CategoryNotFoundException("Category with id: " + id + " not found"));
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: " + id + " not found"));
        categoryRepository.delete(category);
    }
}
