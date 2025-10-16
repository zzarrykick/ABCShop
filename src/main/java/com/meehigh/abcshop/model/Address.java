package com.meehigh.abcshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
    @Data --> Lombok generează automat: getter/setter, toString(),
 equals(), hashCode() și un constructor default (dacă e posibil).

    @Entity - Această adnotare spune framework-ului JPA/Hibernate:
“Această clasă trebuie mapată (legată) la o tabelă din baza de date.”
Astfel, Hibernate creează sau gestionează tabela address în baza de date.
*/

@Data
@Entity
public class Address {
    /*
    @Id → marchează coloana care este cheia primară (PRIMARY KEY) a tabelei.
    @GeneratedValue → îi spune JPA că valoarea acestei chei trebuie generată automat
     (de exemplu, autoincrement).
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotBlank -  face verificare că String-ul nu e null și conține cel puțin un caracter non-space;
     @NotNull - verifică doar că nu este null.*/

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

    @JsonIgnore // Ignorăm pentru JSON — previne recursivitate și concurrent modification
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

/*
//TODO - facem aceste sugestii de imbunatatire a codului?

EXPLICATIE:

@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) pe private User userId;
— relație JPA: multe adrese pot aparține unui user (ManyToOne).

fetch = FetchType.EAGER: când încarci Address, JPA încarcă și User imediat.

cascade = CascadeType.ALL: operațiile făcute pe Address (persist, merge, remove, etc.) se propagă asupra User.

Sugestii de imbunatatire a codului:

1. cascade = CascadeType.ALL pe ManyToOne — periculos: dacă ștergi sau salvezi o adresă,
 operația se poate propaga către User.
  Exemplu nedorit: addressRepository.delete(address) poate duce la
  ștergerea user dacă nu e corect configurat în context. De regulă, nu faci cascade din copil către părinte;
   mai degrabă cascade pe OneToMany dinspre User către Address (dacă e cazul).

  2.FetchType.EAGER — poate duce la N+1 selects sau la încărcări inutile. Pentru ManyToOne EAGER e default,
   dar în practică LAZY e preferabil pentru performanță (atenție: LAZY pe ManyToOne poate necesita
    configurații suplimentare și atenție când folosești DTO-uri sau JSON serialization).

3. Redundanța @NotNull + @NotBlank — @NotBlank deja verifică null,
deci @NotNull nu e necesar pentru Strings.

4. Nicio adnotare @JoinColumn — JPA va crea o coloană implicită user_id.
 E ok, dar dacă vrei alt nume sau specificații (nullable = false) e bine să adaugi
  @JoinColumn(name = "user_id").

5. Validarea se aplică la nivel controller/service — adnotările @NotBlank sunt aplicate la
 validarea bean-urilor (de ex. în @RequestBody @Valid AddressDto sau la salvare cu Validator).

  CONCLUZII:
EAGER = încarcă imediat; comod dar periculos pentru performanță.

LAZY = încarcă la nevoie; recomandat, dar trebuie utilizat corect (DTO-uri / transacții).

CascadeType.ALL = propagă toate operațiile; folosește-l doar pe relații părinte → copil, nu copil → părinte.

Pentru @ManyToOne (Address → User): nu folosi cascade = CascadeType.ALL.
De preferat  fetch = FetchType.LAZY și fără cascade.

Pentru @OneToMany (User → Address): folosește cascade (e.g. CascadeType.ALL) și
 orphanRemoval = true dacă vrei să gestionezi copii din partea părintelui.
   */