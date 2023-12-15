package com.example.finaltaskgfl.services;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.models.UserOrder;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.enums.OrderStatus;
import com.example.finaltaskgfl.repositories.UserOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;
    private final UserService userService;
    private final CarService carService;

    public UserOrderService(UserOrderRepository userOrderRepository, UserService userService, CarService carService) {
        this.userOrderRepository = userOrderRepository;
        this.userService = userService;
        this.carService = carService;
    }

    public List<UserOrder> findAll() {
        return userOrderRepository.findAll();
    }

    public List<UserOrder> findAllUserOrders(Long userId) {
        return userOrderRepository.findAllByUser_UserId(userId);
    }

    @Transactional
    public void addNewOrder(Long userId, Long carId) {
        User user = userService.findUserById(userId).get();
        Car car = carService.findCarById(carId).get();
        UserOrder userOrder = new UserOrder();

        car.setCarStatus(CarStatus.ORDERED);
        userOrder.setUser(user);
        userOrder.setCar(car);
        userOrder.setOrderDate(LocalDate.now());
        userOrder.setOrderStatus(OrderStatus.NEW);

        userOrderRepository.save(userOrder);
    }

    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        UserOrder order = findUserOrderById(orderId).get();
        order.setOrderStatus(newStatus);
        userOrderRepository.save(order);
    }

    private Optional<UserOrder> findUserOrderById(Long orderId) {
        return userOrderRepository.findById(orderId);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        UserOrder userOrder = userOrderRepository.findById(orderId).get();
        Car car = userOrder.getCar();
        userOrder.setOrderStatus(OrderStatus.CANCELED);
        car.setCarStatus(CarStatus.IN_STOCK);

        userOrderRepository.save(userOrder);
    }
}
