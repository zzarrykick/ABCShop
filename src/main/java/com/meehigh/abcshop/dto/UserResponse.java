package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String messageChannel;

    private List<RoleResponse> roles = new ArrayList<>();

    private List<OrderResponse> orders = new ArrayList<>();

    private List<AddressResponse> addresses = new ArrayList<>();



//    // Safe conversion method
//    public static UserResponse convertEntityToResponse(User user) {
//        UserResponse response = new UserResponse();
//        response.setUsername(user.getUsername());
//        response.setFirstName(user.getFirstName());
//        response.setLastName(user.getLastName());
//        response.setCity(user.getCity());
//        response.setEmail(user.getEmail());
//        response.setMessageChannel(user.getMessageChannel());
//
//        // Use safe copying for collections
//        if (user.getRoles() != null) {
//            response.setRoles(user.getRoles().stream()
//                    .map(RoleResponse::convertEntityToResponse)
//                    .collect(Collectors.toSet()));
//        }
//
//        if (user.getOrders() != null) {
//            response.setOrders(user.getOrders().stream()
//                    .map(OrderResponse::convertEntityToResponse)
//                    .collect(Collectors.toList()));
//        }
//
//        if (user.getAddresses() != null) {
//            response.setAddresses(user.getAddresses().stream()
//                    .map(AddressResponse::convertEntityToResponse)
//                    .collect(Collectors.toList()));
//        }
//
//        return response;
//    }
}

