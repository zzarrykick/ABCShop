package com.meehigh.abcshop.controller;

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

    public RoleController(RoleService roleService) {this.roleService = roleService; }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<String> addNewRole(@Valid @RequestBody Role role) {
        roleService.addNewRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role " + role.getId() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable long id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editRole(@PathVariable long id, @RequestBody Role updatedRole) {
        roleService.editRole(id, updatedRole);
        return ResponseEntity.status(HttpStatus.OK).body("Role updated succesfully");
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
