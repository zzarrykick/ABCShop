package com.meehigh.abcshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String city;

    private List<Address> addresses;

    private String email;

    private String password;
    
    private Role role;

    private String messageChannel;
}
