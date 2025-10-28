package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.dto.OrderLineResponse;
import com.meehigh.abcshop.dto.OrderRequest;
import com.meehigh.abcshop.dto.OrderResponse;
import com.meehigh.abcshop.exception.OrderNotFoundException;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Order;
import com.meehigh.abcshop.model.OrderLine;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.AddressRepository;
import com.meehigh.abcshop.repository.OrderRepository;
import com.meehigh.abcshop.repository.UserRepository;
import com.meehigh.abcshop.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Service – conține logica aplicației (ex: calculează totalul comenzii)
@Data
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository,  AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orders = orderRepository.findAll().stream()
                .map(order -> Utils.orderEntityToResponse(order)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found");
        }
        return orders;
    }

    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(order -> Utils.orderEntityToResponse(order))
                .orElseThrow(()-> new OrderNotFoundException("Order with id: " +id + " not found"));
    }

    public List<OrderResponse> getOrderByUserId(Long id) {
        List<OrderResponse> orders = orderRepository.findByUserId(id).stream().map(order -> Utils.orderEntityToResponse(order)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found");
        }
        return orders;
    }

    @Transactional
    public OrderResponse addNewOrder(OrderRequest orderRequest) {
       return Utils.orderEntityToResponse(orderRepository.save(Utils.orderRequestToEntity(orderRequest)));
    }

    @Transactional
    public OrderResponse editOrder(Long id,  OrderRequest updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order with id: " +id + " not found"));

        order.setUser(Utils.userResponseToEntity(updatedOrder.getUser()));
        order.setDeliveryAddress(Utils.addressResponseToEntity(updatedOrder.getDeliveryAddress()));
        order.setUserAddress(Utils.addressResponseToEntity(updatedOrder.getUserAddress()));
        order.setOrderDate(updatedOrder.getOrderDate());
        order.setOrderLines(updatedOrder.getOrderLines().stream()
                .map(orderLineResponse -> Utils.orderLineResponseToEntity(orderLineResponse)).collect(Collectors.toList()));

        return Utils.orderEntityToResponse(orderRepository.save(order));
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Order with id: " +id + " not found"));
        orderRepository.delete(order);
    }
}
