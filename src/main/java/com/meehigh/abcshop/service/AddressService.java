package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.exception.ProductNotFoundException;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.repository.AddressRepsitory;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepsitory addressRepsitory;

    public AddressService(AddressRepsitory addressRepsitory) {
        this.addressRepsitory = addressRepsitory;
    }

    public List<Address> getAllAddress() {
        return addressRepsitory.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepsitory.findById(id)
        .orElseThrow(() -> new AddressNotFoundException("Address with id: " +id + " not found"));
    }

    @Transactional
    public Address addNewAddress(Address address) {
        return addressRepsitory.save(address);
    }

    @Transactional
    public ResponseEntity<String> editAddress(Long id, Address updatedAddress) {
        return addressRepsitory.findById(id).map(address -> {
            updatedAddress.setId(address.getId());
            addressRepsitory.save(updatedAddress);
            return ResponseEntity.status(HttpStatus.OK).body("Address with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteAddress(Long id) {
        return addressRepsitory.findById(id).map(product ->  {
            addressRepsitory.deleteById(product.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Address has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with id: " +id+ " not found"));
    }
}