package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class AddressRequest {

    @NotBlank(message = "Address name cannot be blank")
    private String name;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    private String zipCode;

    private UserResponse user; // doar ID, nu întreaga entitate User
}



/*
Reprezintă datele primite de la client (ex. când creezi sau editezi o adresă).
Adnotările @NotBlank și @NotNull fac validarea automată.
Request: ce vine de la client către server → conține doar informațiile necesare pentru creare/modificare.
AddressRequest – pentru cererile de creare / editare (de la frontend → backend).

 AddressRequest + AddressResponse --> trebuie să fie simple, curate, fără referințe la entități JPA
 în interiorul lor (adică fără User complet în AddressResponse, ci doar userId numeric).*/
