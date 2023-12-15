package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.models.UserAddress;
import com.example.finaltaskgfl.models.enums.Role;
import com.example.finaltaskgfl.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAddressService userAddressService;
    private final PasswordEncoder passwordEncoder;
    private final CarService carService;

    public UserService(UserRepository userRepository, UserAddressService userAddressService, PasswordEncoder passwordEncoder, CarService carService) {
        this.userRepository = userRepository;
        this.userAddressService = userAddressService;
        this.passwordEncoder = passwordEncoder;
        this.carService = carService;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        UserAddress address = user.getAddress();
        if (address != null && address.getAddressId() == null) {
            userAddressService.addAddress(address);
        }
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<Car> getUserFavoriteCars(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getFavoriteCars();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void addCarToFavoritesList(Long id, Long carId) {
        Optional<User> userOptional = userRepository.findById(id);
        Optional<Car> carOptional = carService.findCarById(carId);

        if (userOptional.isPresent() && carOptional.isPresent()) {
            User user = userOptional.get();
            Car car = carOptional.get();

            user.getFavoriteCars().add(car);
            car.getUsersThatAddedToFavorite().add(user);

            userRepository.save(user);
        }
    }

    @Transactional
    public void deleteCarFromFavoritesList(Long id, Long carId) {
        Optional<User> userOptional = userRepository.findById(id);
        Optional<Car> carOptional = carService.findCarById(carId);

        if (userOptional.isPresent() && carOptional.isPresent()) {
            User user = userOptional.get();
            Car car = carOptional.get();

            user.getFavoriteCars().remove(car);
            car.getUsersThatAddedToFavorite().remove(user);

            userRepository.save(user);
        }
    }
}
