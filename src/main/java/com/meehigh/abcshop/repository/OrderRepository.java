package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
