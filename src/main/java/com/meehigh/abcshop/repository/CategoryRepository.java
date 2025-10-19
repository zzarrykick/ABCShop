package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.dto.CategoryRequest;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String categoryName);
}
