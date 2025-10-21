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

    @Transactional
    public UserResponse addNewUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCity(userRequest.getCity());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setMessageChannel(userRequest.getMessageChannel());

        Role role = roleRepository.findByRoleName("ROLE_USER");
        if (role == null) {
            role = checkUserRoleExist("ROLE_USER");
        }
        user.setRoles(List.of(role));

        if(user.getAddresses() != null){
            user.setAddresses(userRequest.getAddresses().stream()
                    .map(addressResponse -> Utils.addressResponseToEntity(addressResponse)).collect(Collectors.toList()));
        }

        return Utils.userEntityToResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponse editUser(Long id, UserRequest updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setMessageChannel(updatedUser.getMessageChannel());

        Role role = roleRepository.findByRoleName("ROLE_USER");
        if (role == null) {
            role = checkUserRoleExist("ROLE_USER");
        }
        existingUser.setRoles(List.of(role));

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
        if (role != null) {
            return role;
        }
        Role newRole = new Role();
        newRole.setRoleName(roleName);
        return roleRepository.save(newRole);
    }
}
