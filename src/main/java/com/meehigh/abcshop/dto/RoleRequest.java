package com.meehigh.abcshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

import java.util.List;

@Data
public class RoleRequest {

    @NotBlank
    private String roleName;

    private List<UserResponse> users = new ArrayList<>();
}
