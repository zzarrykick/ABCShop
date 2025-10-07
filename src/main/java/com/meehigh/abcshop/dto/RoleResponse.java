package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.model.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class RoleResponse {

    private Long id;
    private String roleName;
    private Set<User> users = new HashSet<>();


    // conversie din entitate în DTO
    public static RoleResponse convertEntityToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();

        // extragem datele

        roleResponse.setId(role.getId());
        roleResponse.setRoleName(role.getRoleName());
        roleResponse.setUsers(role.getUsers());


        return roleResponse;
    }
}
