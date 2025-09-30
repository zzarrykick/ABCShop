package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> childrenCategory = new ArrayList<>();*/

    @ManyToOne(fetch = FetchType.EAGER)     // to be clarified
    @JoinColumn(name="parentId")
    private Category parent;
}
