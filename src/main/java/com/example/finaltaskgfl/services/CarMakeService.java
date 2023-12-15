package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.CarMake;
import com.example.finaltaskgfl.repositories.CarMakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMakeService {
    private final CarMakeRepository carMakeRepository;

    public CarMakeService(CarMakeRepository carMakeRepository) {
        this.carMakeRepository = carMakeRepository;
    }

    public List<CarMake> findAll() {
        return carMakeRepository.findAll();
    }
}
