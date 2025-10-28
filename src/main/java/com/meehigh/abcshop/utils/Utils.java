package com.meehigh.abcshop.utils;

import com.meehigh.abcshop.dto.*;
import com.meehigh.abcshop.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    // Address
    public static Address addressRequestToEntity(AddressRequest addressRequest) {
        Address address = new Address();
        address.setName(addressRequest.getName());
        address.setCountry(addressRequest.getCountry());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        if(addressRequest.getUser() != null) {
            address.setUser(userResponseToEntity(addressRequest.getUser()));
        }
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

    public static AddressResponse addressEntityToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();

        // extragem datele
        addressResponse.setId(address.getId());
        addressResponse.setName(address.getName());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setZipCode(address.getZipCode());
        return addressResponse;
    }

    // Category
    public static Category categoryRequestToEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        if (categoryRequest.getParent() != null) {
            category.setParent(categoryRequest.getParent());
        }
        return category;
    }

    public static Category categoryResponseToEntity(CategoryResponse categoryResponse) {
        Category category = new Category();
        category.setName(categoryResponse.getName());
        if(categoryResponse.getParent() != null) {
            category.setParent(categoryResponse.getParent());
        }
        return category;
    }
    public static CategoryResponse categoryEntityToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setParent(category.getParent());
        return categoryResponse;
    }

    // OrderLine
    public static OrderLine orderLineRequestToEntity(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineRequest.getQuantity());
        orderLine.setProduct(productResponseToEntity(orderLineRequest.getProductName()));
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

    public static OrderLine orderLineResponseToEntity(OrderLineResponse orderLineResponse) {
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(productResponseToEntity(orderLineResponse.getProduct()));
        orderLine.setQuantity(orderLineResponse.getQuantity());
        orderLine.setOrder(orderResponseToEntity(orderLineResponse.getOrder()));
        return orderLine;
    }

    // Order
    public static Order orderRequestToEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUser(userResponseToEntity(orderRequest.getUser()));
        order.setDeliveryAddress(addressResponseToEntity(orderRequest.getDeliveryAddress()));
        return order;
    }

    public static Order orderResponseToEntity(OrderResponse orderResponse) {
        Order order = new Order();
        order.setUser(userResponseToEntity(orderResponse.getUser()));
        order.setDeliveryAddress(addressResponseToEntity(orderResponse.getDeliveryAddress()));
        return order;
    }

    public static OrderResponse orderEntityToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        // extragem datele
        orderResponse.setUser(userEntityToResponse(order.getUser()));
        //orderResponse.setUser(basicUserResponse(order.getUser()));
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

//    private static UserResponse basicUserResponse(User user) {
//        if (user == null) return null;
//
//        UserResponse r = new UserResponse();
//        r.setId(user.getId());
//        r.setUsername(user.getUsername());
//        r.setFirstName(user.getFirstName());
//        r.setLastName(user.getLastName());
//        r.setCity(user.getCity());
//        r.setEmail(user.getEmail());
//        r.setMessageChannel(user.getMessageChannel());
//
//        if (user.getRoles() != null) {
//            r.setRoles(user.getRoles().stream()
//                    .map(Utils::roleEntityToResponse)
//                    .collect(Collectors.toList()));
//        }
//
//        // Important: nu setăm orders sau addresses!
//        return r;
//    }

    //Problema: recursie infinită între orderEntityToResponse() ↔ userEntityToResponse().
    //Rezultatul: un ciclu infinit Order -> User -> Order -> User... → JVM intră într-un loop până
    // epuizează memoria stivei → StackOverflowError.
    //Soluția: în orderEntityToResponse() folosim un „user redus” (basicUserResponse)
    // fără referințe circulare.

    // Product
    public static Product productRequestToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(categoryResponseToEntity(productRequest.getCategory()));
        product.setThumbnailUrl(productRequest.getThumbnailUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
//        if(productRequest.getOrderLine() != null) {
//            product.setOrderLine(productRequest.getOrderLine())
//        }
        return product;
    }

    public static Product productResponseToEntity(ProductResponse productResponse) {
        Product product = new Product();
        product.setName(productResponse.getName());
        product.setDescription(productResponse.getDescription());
        product.setCategory(categoryResponseToEntity(productResponse.getCategory()));
        product.setThumbnailUrl(productResponse.getThumbnailUrl());
        product.setPrice(productResponse.getPrice());
        // TODO
        // vrem sa setam stock-ul prin Response ?
        product.setStock(productResponse.getStock());
        //product.setOrderLine(null);
        return product;
    }

    public static ProductResponse productEntityToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(categoryEntityToResponse(product.getCategory()));
        productResponse.setThumbnailUrl(product.getThumbnailUrl());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        return productResponse;
    }


    // Role
    public static RoleResponse roleEntityToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();

        // extragem datele
        roleResponse.setId(role.getId());
        roleResponse.setRoleName(role.getRoleName());

        return roleResponse;
    }

    public static Role roleResponseToEntity(RoleResponse roleResponse) {
        Role role = new Role();
        role.setRoleName(roleResponse.getRoleName());
        return role;
    }

    public static Role roleRequestToEntity(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRoleName(roleRequest.getRoleName());
        role.setUsers(roleRequest.getUsers().stream().map(userResponse -> userResponseToEntity(userResponse)).collect(Collectors.toList()));
        return role;
    }


    public static User userRequestToEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        if(userRequest.getPassword() != null){
            user.setPassword(userRequest.getPassword());
        }
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
                    .collect(Collectors.toList()));
        }

//        if (user.getOrders() != null) {
//            userResponse.setOrders(user.getOrders().stream()
//                    .map(Utils::orderEntityToResponse)
//                    .collect(Collectors.toList()));
//        }
//
//        if (user.getAddresses() != null) {
//            userResponse.setAddresses(user.getAddresses().stream()
//                    .map(Utils::addressEntityToResponse)
//                    .collect(Collectors.toList()));
//        }

        return userResponse;
    }

    public static User userResponseToEntity(UserResponse userResponse) {
        User user = new User();
        user.setId(userResponse.getId());
        user.setUsername(userResponse.getUsername());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setCity(userResponse.getCity());
        user.setEmail(userResponse.getEmail());
        user.setMessageChannel(userResponse.getMessageChannel());
        user.setRoles(userResponse.getRoles().stream()
                .map(role -> roleResponseToEntity(role)).collect(Collectors.toList()));
//        user.setOrders(userResponse.getOrders().stream()
//                .map(order -> orderResponseToEntity(order)).collect(Collectors.toList()));
//        user.setAddresses(userResponse.getAddresses().stream()
//                .map(addressResponse -> addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        return user;
    }

}


