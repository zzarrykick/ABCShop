package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long> {
}
