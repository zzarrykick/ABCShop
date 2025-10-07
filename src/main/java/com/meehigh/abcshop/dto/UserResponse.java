package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponse {

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;


    public static UserResponse convertEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRoles(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
        return userResponse;
    }
}

