package com.meehigh.abcshop.dto;

import java.util.HashSet;
import java.util.Set;

public class RoleRequest {

    private String roleName;
    private Set<UserResponse> users = new HashSet<>();
}
