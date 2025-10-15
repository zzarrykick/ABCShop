package com.meehigh.abcshop.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class RoleRequest {

    private String roleName;
    private Set<UserResponse> users = new HashSet<>();
}
