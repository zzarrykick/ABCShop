package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.RoleRepository;
import com.meehigh.abcshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.meehigh.abcshop.dto.UserResponse.convertEntityToResponse;

@Data
@Service
public class UserService {
    //TODO - cum se trateaza editarea unei comenzi din perspectiva utilizatorului
    //Daca s-ar putea sa facem o metoda speciala, ceva addNewOrderForUser()???
    //Sau se trateaza direct din serviciul de Order?

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToResponse(user))
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public User addNewUser(RegisterRequest userRegisterDTO) {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setCity(userRegisterDTO.getCity());
        user.setUsername(userRegisterDTO.getUsername());
        user.setRoles(Set.of(checkUserRoleExist("ROLE_USER")));
        return userRepository.save(user);
    }

    @Transactional
    public ResponseEntity<String> editUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            updatedUser.setId(user.getId());
            userRepository.save(updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " not found"));
    }

    @Transactional
    public Role checkUserRoleExist(String roleName) {
        Role role = new Role();
        role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role.setRoleName(roleName);
            return roleRepository.save(role);
        }
        return role;
    }
}
