package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.CarRequest;
import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.models.enums.CarRequestStatus;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.repositories.CarRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarRequestService {

    private final CarRequestRepository carRequestRepository;
    private final UserService userService;
    private final CarService carService;

    public CarRequestService(CarRequestRepository carRequestRepository, UserService userService, CarService carService) {
        this.carRequestRepository = carRequestRepository;
        this.userService = userService;
        this.carService = carService;
    }

    public List<CarRequest> findCarRequestsByUserId(Long userId) {
        return carRequestRepository.findCarRequestsByUser_UserId(userId);
    }

    public Optional<CarRequest> findCarRequestById(Long id) {
        return carRequestRepository.findById(id);
    }

    @Transactional
    public void addNewRequest(CarRequest carRequest, Long userId) {
        User user = userService.findUserById(userId).get();
        Car car = carRequest.getCar();

        if (car != null && car.getCarId() == null) {
            car.setCarStatus(CarStatus.REQUESTED);
            carService.addNewCar(car);
        }
        carRequest.setUser(user);
        carRequest.setStatus(CarRequestStatus.OPEN);
        carRequest.setCreatedAt(LocalDate.now());
        carRequestRepository.save(carRequest);
    }

    @Transactional
    public void deleteRequest(Long requestId) {
        carRequestRepository.deleteById(requestId);
    }

    @Transactional
    public void updateRequest(CarRequest updatedCarRequest, Long requestId) {
        updatedCarRequest.setRequestId(requestId);
        carRequestRepository.save(updatedCarRequest);
    }

    public List<CarRequest> findAll() {
        return carRequestRepository.findAll();
    }

    @Transactional
    public void updateRequestStatus(Long requestId, CarRequestStatus newStatus) {
        CarRequest carRequest = findCarRequestById(requestId).get();
        carRequest.setStatus(newStatus);
        carRequestRepository.save(carRequest);
    }
}
