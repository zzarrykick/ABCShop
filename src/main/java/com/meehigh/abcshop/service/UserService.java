package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.exception.UserNotFoundException;
import com.meehigh.abcshop.model.Role;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.RoleRepository;
import com.meehigh.abcshop.repository.UserRepository;
import com.meehigh.abcshop.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException("No users found!");
        }
        return users.stream().map(user -> Utils.userEntityToResponse(user))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id).map( user -> Utils.userEntityToResponse(user))
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("User not found!");
        }
        return Utils.userEntityToResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found!");
        }
        return Utils.userEntityToResponse(user);
    }

    @Transactional
    public UserResponse addNewUser(@Valid RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setCity(registerRequest.getCity());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setMessageChannel(registerRequest.getMessageChannel());
        user.setRoles(List.of(checkUserRoleExist("ROLE_USER")));

        if(user.getAddresses() != null){
            user.setAddresses(registerRequest.getAddresses().stream()
                    .map(addressResponse -> Utils.addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        }

        return Utils.userEntityToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse addNewAdmin(@Valid RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setCity(registerRequest.getCity());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setMessageChannel(registerRequest.getMessageChannel());
        user.setRoles(List.of(checkUserRoleExist("ROLE_ADMIN")));
        if(user.getAddresses() != null){
            user.setAddresses(registerRequest.getAddresses().stream()
                    .map(addressResponse -> Utils.addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        }

        return Utils.userEntityToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse editUser(Long id, @Valid UserRequest updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        existingUser.setMessageChannel(updatedUser.getMessageChannel());
        if(updatedUser.getRoles() != null) {
            existingUser.setRoles(updatedUser.getRoles().stream().map(role -> checkUserRoleExist(role.getRoleName())).collect(Collectors.toList()));
        }
        if(updatedUser.getAddresses() != null){
            existingUser.setAddresses(updatedUser.getAddresses().stream()
                    .map(addressResponse -> Utils.addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        }
        return Utils.userEntityToResponse(userRepository.save(existingUser));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        userRepository.delete(user);
    }

    @Transactional
    public Role checkUserRoleExist(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            Role newRole = new Role();
            newRole.setRoleName(roleName);
            return roleRepository.save(newRole);
        }
        return role;
    }
}
