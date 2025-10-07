package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class AddressResponse {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private User userId;

    // conversie din entitate în DTO
    public static AddressResponse convertEntityToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();

        // extragem datele
        addressResponse.setName(address.getName());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setZipCode(address.getZipCode());
        addressResponse.setUserId(address.getUserId());

        return addressResponse;
    }
}
