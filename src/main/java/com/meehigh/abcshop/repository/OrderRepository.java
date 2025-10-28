package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long id);
}


/*Un Repository este o interfață care definește operațiile de acces la date (citire, scriere, ștergere, căutare).
În Spring Boot, JpaRepository îți oferă toate aceste operații fără să scrii SQL manual.*/