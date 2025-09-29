package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void editUser(Long id, User user) {
        if(userRepository.existsById(id)) {
            userRepository.save(user);
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
