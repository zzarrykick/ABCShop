package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//Request: ce vine de la client către server → conține doar informațiile necesare pentru creare/modificare.
//AddressRequest – pentru cererile de creare / editare (de la frontend → backend).

/* AddressRequest + AddressResponse --> trebuie să fie simple, curate, fără referințe la entități JPA
 în interiorul lor (adică fără User complet în AddressResponse, ci doar userId numeric).*/

@Data
public class AddressRequest {
    //declară DTO-ul AddressRequest --> Numele sugerează: DTO folosit la creare/cerere (request)
    // din client → backend.

    @NotNull
    @NotBlank(message = "Address name cannot be blank")
    private String name;

    @NotNull
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @NotNull
    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotNull
    @NotBlank(message = "Street cannot be blank")
    private String street;

    private String zipCode;




}



/*
//TODO - Explicație clară pt AddressRequest

Reprezintă datele primite de la client (ex. când creezi sau editezi o adresă).

Adnotările @NotBlank și @NotNull fac validarea automată.

Metoda toEntity() transformă DTO-ul într-o entitate JPA (fără să seteze userul încă — se face în service).

Exemplu de utilizare în controller:

@PostMapping
public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody AddressRequest request) {
    Address address = request.toEntity();
    User user = userRepository.findById(request.getUserId()).orElseThrow(...);
    address.setUserId(user);

    Address saved = addressRepository.save(address);
    AddressResponse response = AddressResponse.fromEntity(saved);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
*/