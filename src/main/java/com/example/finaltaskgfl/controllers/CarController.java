package com.example.finaltaskgfl.controllers;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.enums.CarType;
import com.example.finaltaskgfl.models.enums.Fuel;
import com.example.finaltaskgfl.models.enums.Transmission;
import com.example.finaltaskgfl.models.filters.CarFilter;
import com.example.finaltaskgfl.services.CarMakeService;
import com.example.finaltaskgfl.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class CarController {
    private final CarService carService;
    private final CarMakeService carMakeService;

    public CarController(CarService carService, CarMakeService carMakeService) {
        this.carService = carService;
        this.carMakeService = carMakeService;
    }

    @GetMapping("/create_car")
    public String createCar(@ModelAttribute("car") Car car, Model model) {
        addConstantFieldAttributes(model);
        model.addAttribute("make", carMakeService.findAll());
        return "create_car_page";
    }

    @PostMapping("/create_car")
    public String addNewCar(@ModelAttribute("car") Car car) {
        car.setCarStatus(CarStatus.IN_STOCK);
        carService.addNewCar(car);
        return "redirect:/user_page";
    }

    @GetMapping("/search_cars/{id}")
    public String searchCars(@PathVariable Long id, Model model) {
        model = addConstantFieldAttributes(model);
        model.addAttribute("make", carMakeService.findAll());
        model.addAttribute("cars", carService.findCarsInStockNotInUserFavorites(id));
        model.addAttribute("carFilter", new CarFilter());
        return "search_cars_page";
    }

    @PostMapping("/filter_cars/{id}")
    public String filterCars(@PathVariable Long id, @ModelAttribute("carFilter") CarFilter carFilter, Model model) {
        carFilter.setCarStatus(CarStatus.IN_STOCK);
        model = addConstantFieldAttributes(model);
        model.addAttribute("make", carMakeService.findAll());
        model.addAttribute("cars", carService.filterCars(carFilter, id));
        model.addAttribute("carFilter", new CarFilter());
        return "search_cars_page";
    }

    @GetMapping("/all_cars")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "all_cars_page";
    }

    @GetMapping("/delete_car/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/all_cars";
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
