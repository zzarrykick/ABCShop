package com.meehigh.abcshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//@Entity - Reprezintă un tabel din baza de date
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Category name cannot be blank")
    private String name;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> childrenCategory = new ArrayList<>();*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parentId")
    private Category parent;
}
