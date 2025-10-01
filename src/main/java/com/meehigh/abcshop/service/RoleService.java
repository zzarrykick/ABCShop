package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.RoleNotFoundException;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).
                orElseThrow(() -> new RoleNotFoundException("Category with id: " +id+ "not found"));
    }

    @Transactional
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public ResponseEntity<String> editRole(Long id, Role updatedRole) {
        return roleRepository.findById(id).map(role -> {
            updatedRole.setId(role.getId());
            roleRepository.save(updatedRole);
            return ResponseEntity.status(HttpStatus.OK).body("Role with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteRole(Long id) {
        return roleRepository.findById(id).map(category ->  {
            roleRepository.deleteById(category.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Role with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role with id: " +id+ " not found"));
    }
}
