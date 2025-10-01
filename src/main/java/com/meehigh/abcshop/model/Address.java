package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Address name cannot be blank")
    private String name;

    @NotNull
    @NotBlank(message = "Address country cannot be blank")
    private String country;

    @NotNull
    @NotBlank(message = "Address city cannot be blank")
    private String city;

    @NotNull
    @NotBlank(message = "Address street cannot be blank")
    private String street;

    private String zipCode;

    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private User userId;
}
