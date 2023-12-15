package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.UserAddress;
import com.example.finaltaskgfl.repositories.UserAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;

    public UserAddressService(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    public void addAddress(UserAddress address) {
        userAddressRepository.save(address);
    }
}
