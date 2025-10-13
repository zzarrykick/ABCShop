package com.meehigh.abcshop.dto;

import com.meehigh.abcshop.model.Address;

public class AddressRequest {

    private String name;
    private String country;
    private String city;
    private String street;
    private String zipCode;

    //TODO - Cum ar trebui sa arate conversia in acest caz?
    // conversie din entitate în DTO


    public static AddressRequest convertEntityToResponse(Address address) {
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
