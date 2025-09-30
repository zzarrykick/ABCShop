package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public User addNewUser(User user) {
       return userRepository.save(user);
    }

    @Transactional
    public ResponseEntity<String> editUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            updatedUser.setId(user.getId());
            userRepository.save(updatedUser);
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteUser(Long id) {
        return userRepository.findById(id).map(user ->  {
            userRepository.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id+ " has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id: " +id+ " not found"));
    }
}
