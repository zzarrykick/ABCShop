package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(roleRepository.existsById(id)){
            return roleRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public void addNewRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void editRole(Long id,  Role role) {
        if(roleRepository.existsById(id)) {
            roleRepository.save(role);
        }
    }

    @Transactional
    public void deleteRole(Long id) {
        if(roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        }
    }
}
