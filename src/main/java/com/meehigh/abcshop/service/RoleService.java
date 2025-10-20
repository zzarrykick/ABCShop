package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.RoleRequest;
import com.meehigh.abcshop.dto.RoleResponse;
import com.meehigh.abcshop.exception.RoleNotFoundException;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.repository.RoleRepository;
import com.meehigh.abcshop.utils.Utils;
import jdk.jshell.execution.Util;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponse> getAllRoles() {
        List<RoleResponse> roles = roleRepository.findAll().stream().map(role -> Utils.roleEntityToResponse(role)).collect(Collectors.toList());
        if(roles.isEmpty()){
            throw new RoleNotFoundException("No roles found!");
        }
        return roles;
    }

    public RoleResponse getRoleById(Long id) {
        return roleRepository.findById(id).map(role -> Utils.roleEntityToResponse(role))
                .orElseThrow(() -> new RoleNotFoundException("Role with id: " + id + "not found"));
    }

    public RoleResponse getRoleByName(String roleName) {
        return Utils.roleEntityToResponse(roleRepository.findByRoleName(roleName));
    }

    @Transactional
    public RoleResponse addNewRole(RoleRequest roleRequest) {
        return Utils.roleEntityToResponse(roleRepository.save(Utils.roleRequestToEntity(roleRequest)));
    }

    @Transactional
    public RoleResponse editRole(Long id, RoleRequest updatedRole) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role with id: " + id + "not found"));
        role.setRoleName(updatedRole.getRoleName());
        role.setUsers(updatedRole.getUsers().stream().map(userResponse -> Utils.userResponseToEntity(userResponse)).collect(Collectors.toList()));
        return Utils.roleEntityToResponse(roleRepository.save(role));
    }

    @Transactional
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role with id: " + id + "not found"));
        roleRepository.delete(role);
    }
}
