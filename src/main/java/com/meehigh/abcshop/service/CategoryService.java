package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.CategoryNotFoundException;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //TODO
/*    public Category getCategoryByName(String name) {
        return categoryRepository.findAll().map(category -> {
            if (category.getName().equals(name)) {
                return category;
            }
            return null;
        });
    }*/

    /*
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).
                orElseThrow(() -> new CategoryNotFoundException("Category with id: " +id+ "not found"));
    } */

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent category not found"));
    }


    @Transactional
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public ResponseEntity<String> editCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            updatedCategory.setId(category.getId());
            categoryRepository.save(updatedCategory);
            return ResponseEntity.status(HttpStatus.OK).body("Category with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteCategory(Long id){
        return categoryRepository.findById(id).map(category ->  {
            categoryRepository.deleteById(category.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Category with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with id: " +id+ " not found"));
    }
}
