package com.meehigh.abcshop.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
}
