package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleResponse {

    private Long id;
    private String roleName;


    // conversie din entitate în DTO
    public static RoleResponse convertEntityToResponse(Role role) {
        RoleResponse roleResponse = new RoleResponse();

        // extragem datele

        roleResponse.setId(role.getId());
        roleResponse.setRoleName(role.getRoleName());

        return roleResponse;
    }
}
