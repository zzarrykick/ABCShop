package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
