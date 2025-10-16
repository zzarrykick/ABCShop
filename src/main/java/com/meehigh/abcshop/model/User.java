package com.meehigh.abcshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotNull
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;

    @NotNull
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;

    @NotNull
    @NotBlank(message = "City cannot be blank")
    private String city;

    @Email
    @NotNull
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull
    @NotBlank(message = "Password cannot be blank")
    private String password;


    @JsonIgnore // Ignorăm pentru JSON — previne recursivitate și concurrent modification
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private String messageChannel;

    @JsonIgnore // Ignorăm pentru JSON — previne recursivitate și concurrent modification
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore // Ignorăm pentru JSON — previne recursivitate și concurrent modification
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnoreProperties("user")
    private List<Address> addresses = new ArrayList<>();
}
