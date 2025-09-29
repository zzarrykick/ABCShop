package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        if(categoryRepository.existsById(id)){
            return categoryRepository.findById(id).get();
        }
        return null;
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

    @Transactional
    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }

    //TODO
    @Transactional
    public void editCategory(Long id,  Category category) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.save(category);
        }
    }

    @Transactional
    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }
}
