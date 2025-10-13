package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String messageChannel;
    private Set<RoleResponse> roles = new HashSet<>();
    private List<OrderResponse> orders = new ArrayList<>();
    private List<AddressResponse> addresses = new ArrayList<>();


    public static UserResponse convertEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCity(user.getCity());
        userResponse.setEmail(user.getEmail());
        userResponse.setMessageChannel(user.getMessageChannel());
        userResponse.setRoles(user.getRoles().stream()
                .map(role -> RoleResponse.convertEntityToResponse(role)).collect(Collectors.toSet()));
        userResponse.setOrders(user.getOrders().stream()
                .map(order -> OrderResponse.convertEntityToResponse(order)).collect(Collectors.toList()));
        userResponse.setAddresses(user.getAddresses().stream()
                .map(address -> AddressResponse.convertEntityToResponse(address)).collect(Collectors.toList()));
        return userResponse;
    }
}

