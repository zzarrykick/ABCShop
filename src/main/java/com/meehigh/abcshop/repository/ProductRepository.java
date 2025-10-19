package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String productName);
}
