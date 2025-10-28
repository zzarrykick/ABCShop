package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.CategoryRequest;
import com.meehigh.abcshop.dto.CategoryResponse;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controller - Primește cereri HTTP de la utilizator
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/getbyname/{categoryName}")
    public ResponseEntity<List<CategoryResponse>> getCategoryByName(@PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable long id, @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.editCategory(id, categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFound(CategoryNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}

