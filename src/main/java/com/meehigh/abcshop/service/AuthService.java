package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.LoginRequest;
import com.meehigh.abcshop.dto.LoginResponse;
import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.exception.UsernameAlreadyTakenException;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.security.UserDetailsImpl;
import com.meehigh.abcshop.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    public UserResponse registerUser(RegisterRequest registerRequest){
        User existing = userService.getUserRepository().findByUsername(registerRequest.getUsername());
        if (existing != null)
        {
            throw new UsernameAlreadyTakenException("Username is already in use");
        }
        return userService.addNewUser(registerRequest);
    }

    public UserResponse registerAdmin(RegisterRequest registerRequest){
        User existing = userService.getUserRepository().findByUsername(registerRequest.getUsername());
        if (existing != null)
        {
            throw new UsernameAlreadyTakenException("Username is already in use");
        }
        return userService.addNewAdmin(registerRequest);
    }

    public LoginResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority()).toList();

        LoginResponse response = new LoginResponse();
        response.setId(userDetails.getUserNameInfo().getId());
        response.setFirstName(userDetails.getUserNameInfo().getFirstName());
        response.setLastName(userDetails.getUserNameInfo().getLastName());
        response.setUsername(userDetails.getUsername());
        response.setRole(roles.getFirst());

        return response;
    }


}
