package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.repository.AddressRepsitory;
import jakarta.transaction.Transactional;
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
        return addressRepsitory.findById(id).get();
    }

    @Transactional
    public void addNewAddress(Address address) {
        addressRepsitory.save(address);
    }

    @Transactional
    public void editAddress(Long id,  Address address) {
        if (addressRepsitory.existsById(id)) {
            addressRepsitory.save(address);
        }
    }

    @Transactional
    public void deleteAddress(Long id) {
        addressRepsitory.deleteById(id);
    }
}