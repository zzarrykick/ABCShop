package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.model.OrderRequest;
import com.meehigh.abcshop.service.OrderRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderRequestController {

    private final OrderRequestService orderRequestService;

    public OrderRequestController(OrderRequestService orderRequestService) {
        this.orderRequestService = orderRequestService;
    }

    @GetMapping
    public List<OrderRequest> getAllOrderRequest() {
        return orderRequestService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<String> addNewOrderRequest(@Valid @RequestBody OrderRequest orderRequest) {
        orderRequestService.addNewOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("OrderRequest " + orderRequest.getId() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderRequestById(@PathVariable long id) {
        OrderRequest orderRequest = orderRequestService.getOrderById(id);
        return ResponseEntity.ok(orderRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editOrderRequest(@PathVariable long id, @RequestBody OrderRequest updatedOrderRequest) {
        orderRequestService.editOrder(id, updatedOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body("OrderRequest updated succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderRequest(@PathVariable long id) {
        orderRequestService.deleteOrder(id);
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

