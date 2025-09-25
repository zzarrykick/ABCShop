package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
