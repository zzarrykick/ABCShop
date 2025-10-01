package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.OrderRequestNotFoundException;
import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.repository.OrderLineRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }

    public OrderLine getOrderLineById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(()-> new OrderRequestNotFoundException("OrderLine with id: " +id + " not found"));
    }

    @Transactional
    public OrderLine addNewOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    @Transactional
    public ResponseEntity<String> editOrderLine(Long id, OrderLine updatedOrderLine) {
        return orderLineRepository.findById(id).map(orderLine -> {
            updatedOrderLine.setId(orderLine.getId());
            orderLineRepository.save(updatedOrderLine);
            return ResponseEntity.status(HttpStatus.OK).body("OrderLine with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderLine with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteOrderLine(Long id) {
        return orderLineRepository.findById(id).map(orderLine ->  {
            orderLineRepository.deleteById(orderLine.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OrderLine with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderLine with id: " +id+ " not found"));
    }



}
