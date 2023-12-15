package com.example.finaltaskgfl.repositories;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    List<Car> findCarsByCarStatus(CarStatus carStatus);

}
