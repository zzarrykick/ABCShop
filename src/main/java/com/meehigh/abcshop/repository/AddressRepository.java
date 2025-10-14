package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Repository – se ocupă doar de operațiile cu baza de date

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByAddressName(String addressName);
    List<Address> findByUserId(long userId);

}


/*
Repository - Reprezintă interfața prin care aplicația accesează datele
*/