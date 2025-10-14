package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Controller - Primește cereri HTTP de la utilizator
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category " + category.getName() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editCategory(@PathVariable long id, @RequestBody Category updatedCategory) {
        categoryService.editCategory(id, updatedCategory);
        return ResponseEntity.status(HttpStatus.OK).body("Category updated succesfully");
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

