package com.example.finaltaskgfl.controllers;

import com.example.finaltaskgfl.models.CarRequest;
import com.example.finaltaskgfl.models.enums.*;
import com.example.finaltaskgfl.services.CarMakeService;
import com.example.finaltaskgfl.services.CarRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class CarRequestController {

    private final CarRequestService carRequestService;
    private final CarMakeService carMakeService;

    public CarRequestController(CarRequestService carRequestService, CarMakeService carMakeService) {
        this.carRequestService = carRequestService;
        this.carMakeService = carMakeService;
    }

    @GetMapping("/car_requests/{id}")
    public String findCarRequestsByUserId(@PathVariable Long id, Model model) {
        model.addAttribute("carRequests", carRequestService.findCarRequestsByUserId(id));
        return "user_car_requests_page";
    }

    @GetMapping("car_requests")
    public String getAllCarRequests(Model model) {
        model.addAttribute("carRequests", carRequestService.findAll());
        model.addAttribute("requestStatuses", Stream.of(CarRequestStatus.values())
                .map(CarRequestStatus::name).collect(Collectors.toSet()));
        return "all_car_requests_page";
    }

    @GetMapping("/delete_request")
    public String deleteRequest(@RequestParam Long id) {
        carRequestService.deleteRequest(id);
        return "redirect:/car_requests";
    }

    @GetMapping("cancel_request/{id}")
    public String cancelRequest(@RequestParam Long requestId, @PathVariable String id) {
        CarRequest carRequest = carRequestService.findCarRequestById(requestId).get();
        carRequest.setStatus(CarRequestStatus.CANCELED);
        carRequestService.updateRequest(carRequest, requestId);
        return "redirect:/car_requests/{id}";
    }

    @GetMapping("/create_request/{id}")
    public String createCarRequest(@PathVariable Long id, @ModelAttribute("carRequest") CarRequest carRequest, Model model) {
        model = addConstantFieldAttributes(model);
        model.addAttribute("make", carMakeService.findAll());
        return "create_car_request_page";
    }

    @PostMapping("/create_request/{id}")
    public String addCarRequest(@PathVariable Long id, @ModelAttribute("carRequest") CarRequest carRequest) {
        carRequestService.addNewRequest(carRequest, id);
        return "redirect:/car_requests/" + id;
    }

    @PostMapping("/update_status")
    public String updateRequestStatus(@RequestParam Long requestId, @RequestParam CarRequestStatus newStatus) {
        carRequestService.updateRequestStatus(requestId, newStatus);
        return "redirect:/car_requests";
    }

    public Model addConstantFieldAttributes(Model model) {
        model.addAttribute("carType", Stream.of(CarType.values())
                .map(CarType::name)
                .collect(Collectors.toSet()));
        model.addAttribute("fuel", Stream.of(Fuel.values())
                .map(Fuel::name)
                .collect(Collectors.toSet()));
        model.addAttribute("transmission", Stream.of(Transmission.values())
                .map(Transmission::name)
                .collect(Collectors.toSet()));
        return model;
    }
}
