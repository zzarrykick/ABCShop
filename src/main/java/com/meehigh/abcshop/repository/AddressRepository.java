package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.dto.UserResponse;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByName(String addressName);
    List<Address> findByUserId(Long id);
}
