package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
