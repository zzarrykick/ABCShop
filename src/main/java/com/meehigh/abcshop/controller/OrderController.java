package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.OrderRequest;
import com.meehigh.abcshop.dto.OrderResponse;
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
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<OrderResponse> getOrderRequestById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addNewOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.addNewOrder(orderRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> editOrderRequest(@PathVariable long id, @RequestBody OrderRequest updatedOrder) {
        return ResponseEntity.ok(orderService.editOrder(id, updatedOrder));
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

