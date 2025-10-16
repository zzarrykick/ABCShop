package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.RoleRepository;
import com.meehigh.abcshop.repository.UserRepository;
import com.meehigh.abcshop.utils.Utils;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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


    public List<User> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return users.stream().map((user) -> Utils.userEntityToResponse(user))
//                .collect(Collectors.toList());
        return userRepository.findAll();
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
        user.setMessageChannel(userRegisterDTO.getMessageChannel());
        Role role = roleRepository.findByRoleName("ROLE_USER");
        if (role == null) {
            role = checkUserRoleExist("ROLE_USER");
        }
        user.setRoles(List.of(role));
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
        Role role1 = new Role();
        role1.setRoleName(roleName);
        return roleRepository.save(role1);
    }
}
