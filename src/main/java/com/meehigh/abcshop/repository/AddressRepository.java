package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//Repository – se ocupă doar de operațiile cu baza de date

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByName(String addressName);
    List<Address> findByUser(User user);

}


/*
Repository - Reprezintă interfața prin care aplicația accesează datele
*/