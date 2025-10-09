package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepsitory extends JpaRepository<Address, Long> {
    List<Address> findByAddressName(String addressName);
    List<Address> findByUserId(long userId);

}
