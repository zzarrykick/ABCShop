package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class UserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String city;
    private String email;
    private String password;
    private String messageChannel;
    private List<RoleResponse> roles = new ArrayList<>();
    private List<AddressResponse> addresses = new ArrayList<>();
}
