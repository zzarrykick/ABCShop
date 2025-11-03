package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.LoginRequest;
import com.meehigh.abcshop.dto.LoginResponse;
import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.exception.UsernameAlreadyTakenException;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.security.UserDetailsImpl;
import com.meehigh.abcshop.service.AuthService;
import com.meehigh.abcshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth-api")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/admin-register")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(authService.registerAdmin(registerRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }
}
