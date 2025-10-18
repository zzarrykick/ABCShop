package com.meehigh.abcshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import lombok.Data;

//Response: ce pleacă de la server către client → conține tot ce e relevant de afișat
// (inclusiv id, userId, etc.).
//Astfel eviți confuzii, scapi de erori de serializare și respecți principiul Data Transfer Object separation.
//AddressResponse – pentru răspunsurile către client (backend → frontend).

@Data
public class AddressResponse {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String zipCode;
}

/*
AddressResponse:

Reprezintă datele trimise către client (la GET, POST etc.).

Nu conține obiectul User complet — doar userId numeric.

Metoda fromEntity() transformă entitatea JPA în DTO.

//TODO - COD pt INSPIRARTIE

@Data
public class AddressResponse {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private Long userId;

    // conversie din entitate -> DTO
    public static AddressResponse fromEntity(Address address) {
        if (address == null) {
            return null;
        }

        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        response.setName(address.getName());
        response.setCountry(address.getCountry());
        response.setCity(address.getCity());
        response.setStreet(address.getStreet());
        response.setZipCode(address.getZipCode());

        // dacă adresa are un utilizator asociat
        if (address.getUserId() != null) {
            response.setUserId(address.getUserId().getId());
        }

        return response;
    }
}

//TODO - Exemplu de utilizare în controller:

@GetMapping("/{id}")
public ResponseEntity<AddressResponse> getAddress(@PathVariable Long id) {
    Address address = addressRepository.findById(id).orElseThrow(...);
    return ResponseEntity.ok(AddressResponse.fromEntity(address));
}

⚠️ De ce trebuie separate Request și Response?

Request: ce vine de la client către server → conține doar informațiile necesare pentru creare/modificare.

Response: ce pleacă de la server către client → conține tot ce e relevant de afișat (inclusiv id, userId, etc.).

Astfel eviți confuzii, scapi de erori de serializare și respecți principiul Data Transfer Object separation.
*/