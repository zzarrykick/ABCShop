package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.RoleRequest;
import com.meehigh.abcshop.dto.RoleResponse;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @GetMapping("/getbyname/{roleName}")
    public ResponseEntity<RoleResponse> getRoleByName(@PathVariable String roleName) {
        return ResponseEntity.ok(roleService.getRoleByName(roleName));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> addNewRole(@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.addNewRole(roleRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> editRole(@PathVariable long id, @RequestBody RoleRequest updatedRole) {
        return ResponseEntity.ok(roleService.editRole(id, updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ErrorResponse handleRoleNotFound(RoleNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}
