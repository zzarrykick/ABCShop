package com.meehigh.abcshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String roleName;

    @JsonIgnore  // <-- IMPORTANT, oprește serializarea în lanț
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) //previne încărcarea dublă
    private Set<User> users = new HashSet<>();
}
