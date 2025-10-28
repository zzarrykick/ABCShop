package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.OrderLineRequest;
import com.meehigh.abcshop.dto.OrderLineResponse;
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
    public ResponseEntity<List<OrderLineResponse>> getAllOrderLine() {
        return ResponseEntity.ok(orderLineService.getAllOrderLines());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<OrderLineResponse> getOrderLineById(@PathVariable long id) {
        return ResponseEntity.ok(orderLineService.getOrderLineById(id));
    }

    @PostMapping
    public ResponseEntity<OrderLineResponse> addNewOrderLine(@Valid @RequestBody OrderLineRequest orderLineRequest) {
        return ResponseEntity.ok(orderLineService.addNewOrderLine(orderLineRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderLineResponse> editOrderLine(@PathVariable long id, @RequestBody OrderLineRequest updatedOrderLine) {
        return ResponseEntity.ok(orderLineService.editOrderLine(id, updatedOrderLine));
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

