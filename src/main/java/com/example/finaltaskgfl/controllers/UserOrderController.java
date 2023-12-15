package com.example.finaltaskgfl.controllers;

import com.example.finaltaskgfl.models.enums.OrderStatus;
import com.example.finaltaskgfl.services.UserOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class UserOrderController {
    private final UserOrderService userOrderService;

    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @GetMapping("/all_orders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", userOrderService.findAll());
        model.addAttribute("orderStatuses", Stream.of(OrderStatus.values())
                .map(OrderStatus::name).collect(Collectors.toSet()));
        return "all_orders_page";
    }

    @GetMapping("/user_orders/{id}")
    public String getUserOrders(@PathVariable Long id, Model model) {
        model.addAttribute("userOrders", userOrderService.findAllUserOrders(id));
        return "user_orders_page";
    }

    @GetMapping("/create_order/{id}")
    public String createOrder(@PathVariable Long id, @RequestParam("carId") Long carId) {
        userOrderService.addNewOrder(id, carId);
        return "redirect:/user_orders/" + id;
    }

    @PostMapping("/update_order_status")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus newStatus) {
        userOrderService.updateOrderStatus(orderId, newStatus);
        return "redirect:/all_orders";
    }

    @GetMapping("/cancel_order/{id}")
    public String cancelOrder(@PathVariable Long id, @RequestParam Long orderId) {
        userOrderService.cancelOrder(orderId);
        return "redirect:/user_orders/" + id;
    }
}
