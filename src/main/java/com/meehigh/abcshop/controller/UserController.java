package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.RegisterRequest;
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

    public UserController(UserService userService) {this.userService = userService; }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addNewUsers(@Valid @RequestBody RegisterRequest userRequestDto) {
        userService.addNewUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User " + userRequestDto.getUsername() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editUser(@PathVariable long id, @RequestBody User updatedUser) {
        userService.editUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body("User updated succesfully");
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
