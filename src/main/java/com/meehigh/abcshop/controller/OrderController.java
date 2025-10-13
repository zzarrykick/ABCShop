package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrderRequest() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<String> addNewOrderRequest(@Valid @RequestBody Order order) {
        orderService.addNewOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("OrderRequest " + order.getId() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderRequestById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editOrderRequest(@PathVariable long id, @RequestBody Order updatedOrder) {
        orderService.editOrder(id, updatedOrder);
        return ResponseEntity.status(HttpStatus.OK).body("OrderRequest updated succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderRequest(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("OrderRequest deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderRequestNotFoundException.class)
    public ErrorResponse handleOrderRequestNotFound(OrderRequestNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}

