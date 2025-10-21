package com.meehigh.abcshop.dto;

import lombok.Data;

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
Response:
- ce pleacă de la server către client → conține tot ce e relevant de afișat (inclusiv id, userId, etc.)
- pt răspunsurile către client (backend → frontend), reprezintă datele trimise către client (la GET, POST etc.)

Recomandat:  Request și Response separate:

Request: ce vine de la client către server → conține doar informațiile necesare pentru creare/modificare.
Response: ce pleacă de la server către client → conține tot ce e relevant de afișat (inclusiv id, userId, etc.).

Evitare confuzii, scapam de erori de serializare și respecțam principiul Data Transfer Object separation.
*/