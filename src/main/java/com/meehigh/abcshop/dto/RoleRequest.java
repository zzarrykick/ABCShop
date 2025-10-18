package com.meehigh.abcshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class RoleRequest {

    private String roleName;
    private List<UserResponse> users = new ArrayList<>();
}
