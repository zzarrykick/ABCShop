package com.meehigh.abcshop.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String fullName;
    private String email;
    private String role;
}
