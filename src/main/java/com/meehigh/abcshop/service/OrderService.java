package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.OrderNotFoundException;
import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.repository.OrderRepository;
import com.meehigh.abcshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
//Service – conține logica aplicației (ex: calculează totalul comenzii)
@Data
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order with id: " +id + " not found"));
    }

    @Transactional
    public Order addNewOrder(Order order) {
       return orderRepository.save(order);
    }

    @Transactional
    public ResponseEntity<String> editOrder(Long id,  Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            updatedOrder.setId(order.getId());
            orderRepository.save(updatedOrder);
            return ResponseEntity.status(HttpStatus.OK).body("Order with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteOrder(Long id) {
        return orderRepository.findById(id).map(order ->  {
            orderRepository.deleteById(order.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Order with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id: " +id+ " not found"));
    }
}
