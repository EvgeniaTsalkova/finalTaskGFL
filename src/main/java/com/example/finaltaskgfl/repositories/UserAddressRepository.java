package com.example.finaltaskgfl.repositories;

import com.example.finaltaskgfl.models.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
