package com.example.finaltaskgfl.controllers;

import com.example.finaltaskgfl.validation.UserValidator;
import com.example.finaltaskgfl.models.User;
import com.example.finaltaskgfl.security.AppUserDetails;
import com.example.finaltaskgfl.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;

    public UserController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user_page")
    public String showUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "user_page";
    }

    @GetMapping("/create_user")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "/create_user_page";
    }

    @PostMapping("/create_user")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/create_user";
        }
        userService.addNewUser(user);
        return "redirect:/login";
    }

    @GetMapping("/favorite_cars/{id}")
    public String getUserFavoriteCars(@PathVariable Long id, Model model) {
        model.addAttribute("favoriteCars", userService.getUserFavoriteCars(id));
        return "user_favorite_cars_page";
    }

    @GetMapping("/add_car_to_favorites/{id}")
    public String addCarToFavoritesList(@PathVariable Long id, @RequestParam Long carId) {
        userService.addCarToFavoritesList(id, carId);
        return "redirect:/search_cars/" + id;
    }

    @GetMapping("/delete_car_from_fav/{id}")
    public String deleteCarFromFavoritesList(@PathVariable Long id, @RequestParam Long carId) {
        userService.deleteCarFromFavoritesList(id, carId);
        return "redirect:/favorite_cars/" + id;
    }
}
