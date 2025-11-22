package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.OrderLineRequest;
import com.meehigh.abcshop.dto.OrderLineResponse;
import com.meehigh.abcshop.exception.OrderLineNotFoundException;
import com.meehigh.abcshop.exception.OrderNotFoundException;
import com.meehigh.abcshop.exception.ProductNotFoundException;
import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.repository.OrderLineRepository;
import com.meehigh.abcshop.repository.ProductRepository;
import com.meehigh.abcshop.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    private ProductRepository productRepository;
    public OrderLineService(OrderLineRepository orderLineRepository,  ProductRepository productRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepository;
    }

    public List<OrderLineResponse> getAllOrderLines() {
        List<OrderLineResponse> orderLines = orderLineRepository.findAll().stream().map(orderLine -> Utils.orderLineEntityToResponse(orderLine))
                .collect(Collectors.toList());
        if (orderLines.isEmpty()) {
            throw new OrderLineNotFoundException("No orderlines found");
        }
        return orderLines;
    }

    public OrderLineResponse getOrderLineById(Long id) {
        return orderLineRepository.findById(id).map(orderLine -> Utils.orderLineEntityToResponse(orderLine))
                .orElseThrow(()-> new OrderLineNotFoundException(("OrderLine with id: " +id + " not found")));
    }

    @Transactional
    public OrderLineResponse addNewOrderLine(OrderLineRequest orderLineRequest) {
        Product product = productRepository.findById(orderLineRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " +orderLineRequest.getProductId() + " not found"));
        return Utils.orderLineEntityToResponse(orderLineRepository.save(Utils.orderLineRequestToEntity(orderLineRequest, product)));
    }

    @Transactional
    public OrderLineResponse editOrderLine(Long id, OrderLineRequest updatedOrderLine) {
        OrderLine orderLine = orderLineRepository.findById(id)
                .orElseThrow(() -> new OrderLineNotFoundException("OrderLine with id: " +id + " not found"));
//        orderLine.setProduct(Utils.productResponseToEntity(updatedOrderLine.getProductName()));
        orderLine.setQuantity(updatedOrderLine.getQuantity());
        orderLine.setOrder(Utils.orderResponseToEntity(updatedOrderLine.getOrder()));

        return Utils.orderLineEntityToResponse(orderLineRepository.save(orderLine));
    }

    @Transactional
    public void deleteOrderLine(Long id) {
        OrderLine orderLine = orderLineRepository.findById(id)
                .orElseThrow(() -> new OrderLineNotFoundException("OrderLine with id: " +id + " not found"));
        orderLineRepository.delete(orderLine);
    }
}
