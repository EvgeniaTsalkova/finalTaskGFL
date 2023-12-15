package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.filters.CarFilter;
import com.example.finaltaskgfl.repositories.CarRepository;
import com.example.finaltaskgfl.specifications.CarSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findCarsInStockNotInUserFavorites(Long userId) {
        List<Car> allCarsInStock = carRepository.findCarsByCarStatus(CarStatus.IN_STOCK);
        removeCarsIfInUserFavorites(allCarsInStock, userId);
       return allCarsInStock;
    }

    public Optional<Car> findCarById(Long carId) {
        return carRepository.findById(carId);
    }

    @Transactional
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> filterCars(CarFilter carFilter, Long userId) {
        Specification<Car> spec = Specification.where(null);

        if (carFilter.getMake() != null) {
            spec = spec.and(CarSpecifications.makeEquals(carFilter.getMake()));
        }

        if (carFilter.getModel() != null && !Objects.equals(carFilter.getModel(), "")) {
            spec = spec.and(CarSpecifications.modelEquals(carFilter.getModel()));
        }

        if (carFilter.getTransmission() != null) {
            spec = spec.and(CarSpecifications.transmissionEquals(carFilter.getTransmission()));
        }

        if (carFilter.getColor() != null && !Objects.equals(carFilter.getModel(), "")) {
            spec = spec.and(CarSpecifications.colorEquals(carFilter.getColor()));
        }

        if (carFilter.getFuel() != null) {
            spec = spec.and(CarSpecifications.fuelEquals(carFilter.getFuel()));
        }

        if (carFilter.getCarType() != null) {
            spec = spec.and(CarSpecifications.carTypeEquals(carFilter.getCarType()));
        }

        if (carFilter.getIsNew() != null) {
            spec = spec.and(CarSpecifications.isNewEquals(carFilter.getIsNew()));
        }

        if (carFilter.getCarStatus() != null) {
            spec = spec.and(CarSpecifications.statusEquals(carFilter.getCarStatus()));
        }

        if (carFilter.getPriceMin() != null && carFilter.getPriceMax() != null) {
            spec = spec.and(CarSpecifications.priceBetween(carFilter.getPriceMin(), carFilter.getPriceMax()));
        }

        if (carFilter.getYearMin() != null && carFilter.getYearMax() != null) {
            spec = spec.and(CarSpecifications.yearBetween(carFilter.getYearMin(), carFilter.getYearMax()));
        }

        if (carFilter.getMileageMin() != null && carFilter.getMileageMax() != null) {
            spec = spec.and(CarSpecifications.mileageBetween(carFilter.getMileageMin(), carFilter.getMileageMax()));
        }

        if (carFilter.getEngineDisplacementMin() != null && carFilter.getEngineDisplacementMax() != null) {
            spec = spec.and(CarSpecifications.engineDisplacementBetween(carFilter.getEngineDisplacementMin(), carFilter.getEngineDisplacementMax()));
        }

        List<Car> cars = carRepository.findAll(spec);

        return removeCarsIfInUserFavorites(cars, userId);
    }

    public List<Car> findAll() {
        return  carRepository.findAll();
    }

    public List<Car> removeCarsIfInUserFavorites(List<Car> cars, Long userId) {
        cars.removeIf(car -> car.getUsersThatAddedToFavorite().stream()
                .map(User::getUserId).toList().contains(userId));
        return cars;
    }
}
