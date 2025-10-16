package com.meehigh.abcshop.utils;

import com.meehigh.abcshop.dto.*;
import com.meehigh.abcshop.model.*;

import java.util.stream.Collectors;

public class Utils {

    // (Conversie DTO -> Entitate)
    // Conversie AddressRequest → Address
    public static Address addressRequestToEntity(AddressRequest addressRequest) {
        Address address = new Address(); //Creez un obiect nou Address, copiez câmpurile din AddressRequest (DTO primit de la frontend).
        address.setName(addressRequest.getName());
        address.setCountry(addressRequest.getCountry());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        return address;
    }

    public static Address addressResponseToEntity(AddressResponse addressResponse) {
        Address address = new Address();
        address.setName(addressResponse.getName());
        address.setCountry(addressResponse.getCountry());
        address.setCity(addressResponse.getCity());
        address.setStreet(addressResponse.getStreet());
        address.setStreet(addressResponse.getZipCode());
        return address;
    }

    // conversie din entitate în DTO

    public static AddressResponse addressEntityToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();

        // extragem datele
        addressResponse.setName(address.getName());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setZipCode(address.getZipCode());
        addressResponse.setUser(address.getUser());

        return addressResponse;
    }

    public static Category categoryRequestToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setParent(categoryRequest.getParent());
        return category;
    }

    // conversie din entitate în DTO
    public static CategoryResponse categoryEntityToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();

        // extragem datele
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setParent(category.getParent());
        return categoryResponse;
    }

    public static OrderLine orderLineRequestToEntity(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineRequest.getQuantity());
        orderLine.setProduct(orderLineRequest.getProductName());
        return orderLine;
    }

    // conversie din entitate în DTO
    public static OrderLineResponse orderLineEntityToResponse(OrderLine orderLine) {
        OrderLineResponse orderLineResponse = new OrderLineResponse();

        // extragem datele
        orderLineResponse.setQuantity(orderLine.getQuantity());
        orderLineResponse.setProduct(productEntityToResponse(orderLine.getProduct()));
        return orderLineResponse;
    }

    public static Order oderRequestToEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUser(userResponseToEntity(orderRequest.getUser()));
        order.setDeliveryAddress(addressResponseToEntity(orderRequest.getDeliveryAddress()));
        return order;
    }

    private static Order orderResponseToEntity(OrderResponse orderResponse) {
        Order order = new Order();
        order.setUser(userResponseToEntity(orderResponse.getUser()));
        order.setDeliveryAddress(addressResponseToEntity(orderResponse.getDeliveryAddress()));
        return order;
    }



    // conversie din entitate în DTO
    public static OrderResponse orderEntityToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        // extragem datele
        orderResponse.setUser(userEntityToResponse(order.getUser()));
        orderResponse.setDeliveryAddress(addressEntityToResponse(order.getDeliveryAddress()));
        orderResponse.setUserAddress(addressEntityToResponse(order.getUserAddress()));
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setOrderLines(
                order.getOrderLines().stream()
                        .map(orderLine -> orderLineEntityToResponse(orderLine))
                        .collect(Collectors.toList())
        );
        orderResponse.setStatus(order.getStatus());

        return orderResponse;
    }

    // conversie din entitate în DTO
    //Conversie Product → ProductResponse
    public static ProductResponse productEntityToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();

        // extragem datele
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(categoryEntityToResponse(product.getCategory()));
        productResponse.setThumbnailUrl(product.getThumbnailUrl());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        return productResponse;
    }


    // Conversie din entitate în DTO
    // Conversie Role → RoleResponse
    public static RoleResponse roleEntityToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();

        // extragem datele
        roleResponse.setId(role.getId());
        roleResponse.setRoleName(role.getRoleName());

        return roleResponse;
    }

    private static Role roleResponseToEntity(RoleResponse roleResponse) {
        Role role = new Role();
        role.setRoleName(roleResponse.getRoleName());
        return role;
    }

    public static User userRequestToEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setCity(userRequest.getCity());
        user.setEmail(userRequest.getEmail());
        user.setMessageChannel(userRequest.getMessageChannel());
        user.setRoles(userRequest.getRoles().stream()
                .map(role -> roleResponseToEntity(role)).collect(Collectors.toList()));
        user.setAddresses(userRequest.getAddresses().stream()
                .map(addressResponse -> addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        return user;
    }

    //Conversie User → UserResponse
    public static UserResponse userEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        //extragem datele
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCity(user.getCity());
        userResponse.setEmail(user.getEmail());
        userResponse.setMessageChannel(user.getMessageChannel());
        if (user.getRoles() != null) {
            userResponse.setRoles(user.getRoles().stream()
                    .map(Utils::roleEntityToResponse)
                    .collect(Collectors.toSet()));
        }

        if (user.getOrders() != null) {
            userResponse.setOrders(user.getOrders().stream()
                    .map(Utils::orderEntityToResponse)
                    .collect(Collectors.toList()));
        }

        if (user.getAddresses() != null) {
            userResponse.setAddresses(user.getAddresses().stream()
                    .map(Utils::addressEntityToResponse)
                    .collect(Collectors.toList()));
        }

        return userResponse;
    }

    private static User userResponseToEntity(UserResponse userResponse) {
        User user = new User();
        user.setUsername(userResponse.getUsername());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setCity(userResponse.getCity());
        user.setEmail(userResponse.getEmail());
        user.setMessageChannel(userResponse.getMessageChannel());
        user.setRoles(userResponse.getRoles().stream()
                .map(role -> roleResponseToEntity(role)).collect(Collectors.toList()));
        user.setOrders(userResponse.getOrders().stream()
                .map(order -> orderResponseToEntity(order)).collect(Collectors.toList()));
        user.setAddresses(userResponse.getAddresses().stream()
                .map(addressResponse -> addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        return user;
    }
}


