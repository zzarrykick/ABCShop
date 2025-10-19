package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Category name cannot be blank")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="parentId")
    private Category parent;
}
