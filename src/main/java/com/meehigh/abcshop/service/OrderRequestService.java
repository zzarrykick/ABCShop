package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.OrderRequestNotFoundException;
import com.meehigh.abcshop.model.OrderRequest;
import com.meehigh.abcshop.repository.OrderRequestRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class OrderRequestService {
    private final OrderRequestRepository orderRequestRepository;

    public OrderRequestService(OrderRequestRepository orderRequestRepository) {
        this.orderRequestRepository = orderRequestRepository;
    }

    public List<OrderRequest> getAllOrders() {
        return orderRequestRepository.findAll();
    }

    public OrderRequest getOrderById(Long id) {
        return orderRequestRepository.findById(id)
                .orElseThrow(()-> new OrderRequestNotFoundException("Order with id: " +id + " not found"));
    }

    @Transactional
    public OrderRequest addNewOrder(OrderRequest orderRequest) {
       return orderRequestRepository.save(orderRequest);
    }

    @Transactional
    public ResponseEntity<String> editOrder(Long id,  OrderRequest updatedOrderRequest) {
        return orderRequestRepository.findById(id).map(order -> {
            updatedOrderRequest.setId(order.getId());
            orderRequestRepository.save(updatedOrderRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Order with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteOrder(Long id) {
        return orderRequestRepository.findById(id).map(order ->  {
            orderRequestRepository.deleteById(order.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Order with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order with id: " +id+ " not found"));
    }
}
