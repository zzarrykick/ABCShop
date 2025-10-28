package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


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

    //private List<OrderResponse> orders = new ArrayList<>();

    //private List<AddressResponse> addresses = new ArrayList<>();

}

