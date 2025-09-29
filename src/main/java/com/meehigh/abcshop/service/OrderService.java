package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        if(orderRepository.existsById(id)){
            return orderRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void editOrder(Long id,  Order order) {
        if(orderRepository.existsById(id)) {
            orderRepository.save(order);
        }
    }

    @Transactional
    public void deleteOrder(Long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }
}
