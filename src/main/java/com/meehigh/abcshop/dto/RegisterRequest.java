package com.meehigh.abcshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RegisterRequest {
//    @NotBlank(message = "Username cannot be blank")
//    private String username;
//
//    @NotBlank(message = "Firstname cannot be blank")
//    private String firstName;
//
//    @NotBlank(message = "Lastname cannot be blank")
//    private String lastName;
//
//    @NotBlank(message = "City cannot be blank")
//    private String city;
//
//    @Email
//    @NotBlank(message = "Email cannot be blank")
//    private String email;
//
//    @NotBlank(message = "Password cannot be blank")
//    private String password;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String messageChannel;
    private Set<RoleResponse> roles = new HashSet<>();
    private List<AddressResponse> addresses = new ArrayList<>();




}
