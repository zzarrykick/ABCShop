package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.service.OrderLineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orderlines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping
    public List<OrderLine> getAllOrderLine() {
        return orderLineService.getAllOrderLines();
    }

    @PostMapping
    public ResponseEntity<String> addNewOrderLine(@Valid @RequestBody OrderLine orderLine) {
        orderLineService.addNewOrderLine(orderLine);
        return ResponseEntity.status(HttpStatus.CREATED).body("OrderLine " + orderLine.getId() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderLineById(@PathVariable long id) {
        OrderLine orderLine = orderLineService.getOrderLineById(id);
        return ResponseEntity.ok(orderLine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editOrderLine(@PathVariable long id, @RequestBody OrderLine updatedOrderLine) {
        orderLineService.editOrderLine(id, updatedOrderLine);
        return ResponseEntity.status(HttpStatus.OK).body("OrderLine updated succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderLine(@PathVariable long id) {
        orderLineService.deleteOrderLine(id);
        return ResponseEntity.ok("OrderLine deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderLineNotFoundException.class)
    public ErrorResponse handleOrderLineNotFound(OrderLineNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}

