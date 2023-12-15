package com.example.finaltaskgfl.repositories;

import com.example.finaltaskgfl.models.CarRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRequestRepository extends JpaRepository<CarRequest, Long> {
    List<CarRequest> findCarRequestsByUser_UserId(Long userId);
}
