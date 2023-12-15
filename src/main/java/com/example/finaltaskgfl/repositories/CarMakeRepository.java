package com.example.finaltaskgfl.repositories;

import com.example.finaltaskgfl.models.CarMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarMakeRepository extends JpaRepository<CarMake, Long> {
}
