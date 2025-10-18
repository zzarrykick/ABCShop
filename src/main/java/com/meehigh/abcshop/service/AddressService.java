package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.AddressRequest;
import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.exception.UserNotFoundException;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.repository.AddressRepository;
import com.meehigh.abcshop.repository.UserRepository;
import com.meehigh.abcshop.utils.Utils;
import jdk.jshell.execution.Util;
import org.springframework.transaction.annotation.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<AddressResponse> getAllAddress() {
        return addressRepository.findAll().stream()
                .map(address -> Utils.addressEntityToResponse(address))
                .collect(Collectors.toList());
    }

    public AddressResponse getAddressById(Long id) {
        return addressRepository.findById(id)
                .map( address ->  Utils.addressEntityToResponse(address))
                .orElseThrow(() -> new AddressNotFoundException("Address with id: " + id + " not found"));
    }

    public List<AddressResponse> getAddressByName(String addressName) {
        List<AddressResponse> addressResponse = addressRepository.findByName(addressName).stream()
                .map(address -> Utils.addressEntityToResponse(address)).collect(Collectors.toList());
        if(!addressResponse.isEmpty()){
            return addressResponse;
        }
        throw (new AddressNotFoundException("Address with name: " + addressName + " not found"));
    }

    public List<AddressResponse> getAddressByUserId(Long userId) {
        User userFound = new User();
        try {
            userFound = userRepository.findById(userId).get();
        }catch(Exception e){
            throw (new UserNotFoundException("User with id: " + userId + " not found"));
        }
        List<AddressResponse> addressResponse = addressRepository.findByUser(userFound).stream()
                .map(address -> Utils.addressEntityToResponse(address)).collect(Collectors.toList());
       if(!addressResponse.isEmpty()){
            return addressResponse;
        }
       throw (new AddressNotFoundException("No addresses for user with id: " + userId + " were found"));

    }

    @Transactional
    public AddressResponse addNewAddress(AddressRequest addressRequest) {
        return Utils.addressEntityToResponse(addressRepository.save(Utils.addressRequestToEntity(addressRequest)));
    }

    @Transactional
    public ResponseEntity<String> editAddress(Long id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            updatedAddress.setId(address.getId());
            addressRepository.save(updatedAddress);
            return ResponseEntity.status(HttpStatus.OK).body("Address with id: " + id + " has been updated successfully");
        }).orElseThrow(() -> new AddressNotFoundException("Address with id: " + id + " not found."));
    }

    @Transactional
    public ResponseEntity<String> deleteAddress(Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.deleteById(address.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Address has been deleted");
        }).orElseThrow(() -> new AddressNotFoundException("Address with id: " + id + " not found."));
    }
}

/*
@Service → spune lui Spring că această clasă este un “bean”
care oferă funcționalități de serviciu (logică de business).

@Data → vine din Lombok, și generează automat: getteri, setteri, toString, equals, hashCode.


*/