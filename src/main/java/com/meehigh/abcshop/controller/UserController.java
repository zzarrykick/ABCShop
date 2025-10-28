package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.RegisterRequest;
import com.meehigh.abcshop.dto.UserRequest;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService; }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByUsername(email));
    }

    @PostMapping
    public ResponseEntity<UserResponse> addNewUsers(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.addNewUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable long id, @Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.editUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}
