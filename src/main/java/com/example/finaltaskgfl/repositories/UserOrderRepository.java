package com.example.finaltaskgfl.repositories;

import com.example.finaltaskgfl.models.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findAllByUser_UserId(Long userId);
}
