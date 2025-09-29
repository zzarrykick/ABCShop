package com.meehigh.abcshop.repository;

import com.meehigh.abcshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
