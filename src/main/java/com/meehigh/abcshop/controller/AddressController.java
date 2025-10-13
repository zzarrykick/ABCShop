package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @PostMapping
    public ResponseEntity<String> addNewAddress(@Valid @RequestBody Address address) {
        addressService.addNewAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category " + address.getName() + " created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable long id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{addressName}")
    public ResponseEntity<?> getAddressByName(@PathVariable String addressName) {
        List<Address> address = addressService.getAddressByName(addressName);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable long userId) {
        List<Address> address = addressService.getAddressByUserId(userId);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editAddress(@PathVariable long id, @RequestBody Address updatedAddress) {
        addressService.editAddress(id, updatedAddress);
        return ResponseEntity.status(HttpStatus.OK).body("Address updated succesfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AddressNotFoundException.class)
    public ErrorResponse handleAddressNotFound(AddressNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}
