package com.example.finaltaskgfl.models;

import com.example.finaltaskgfl.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    @NotNull(message = "First name should not be empty")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    @NotNull(message = "Last name should not be empty")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email should not be empty")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Phone number should not be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private UserAddress address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToMany(mappedBy = "usersThatAddedToFavorite")
    private List<Car> favoriteCars;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CarRequest> carRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders;

    public User(Long id, String firstName, String lastName, String email, String password, String phoneNumber,
                UserAddress address, Role role) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.favoriteCars = new ArrayList<>();
        this.carRequests = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Car> getFavoriteCars() {
        return favoriteCars;
    }

    public void setFavoriteCars(List<Car> preferredCars) {
        this.favoriteCars = preferredCars;
    }

    public List<CarRequest> getCarRequests() {
        return carRequests;
    }

    public void setCarRequests(List<CarRequest> carRequests) {
        this.carRequests = carRequests;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }
}
