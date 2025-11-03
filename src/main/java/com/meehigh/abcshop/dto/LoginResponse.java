package com.meehigh.abcshop.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String username;
    private String role;
}
