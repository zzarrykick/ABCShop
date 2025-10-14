package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
//Service - Ține logica aplicației, folosește repository-ul
@Data
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address with id: " + id + " not found"));
    }

    public List<Address> getAddressByName(String addressName) {
        try {
            return addressRepository.findByAddressName(addressName);
        } catch (Exception e) {
            throw (new AddressNotFoundException("Address with name: " + addressName + "not found"));
        }
    }

    public List<Address> getAddressByUserId(long userId) {
        try {
            return addressRepository.findByUserId(userId);
        } catch (Exception e) {
            throw (new AddressNotFoundException("No addresses for user with id: " + userId + " were found"));
        }
    }

    @Transactional
    public Address addNewAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional
    public ResponseEntity<String> editAddress(Long id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            updatedAddress.setId(address.getId());
            addressRepository.save(updatedAddress);
            return ResponseEntity.status(HttpStatus.OK).body("Address with id: " + id + " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with id: " + id + " not found"));
    }

    @Transactional
    public ResponseEntity<String> deleteAddress(Long id) {
        return addressRepository.findById(id).map(product -> {
            addressRepository.deleteById(product.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Address has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with id: " + id + " not found"));
    }
}