package com.example.finaltaskgfl.models;

import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.enums.CarType;
import com.example.finaltaskgfl.models.enums.Fuel;
import com.example.finaltaskgfl.models.enums.Transmission;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "make")
    private CarMake make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "color")
    private String color;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "transmission")
    private Transmission transmission;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "fuel")
    private Fuel fuel;

    @Column(name="mileage")
    private Integer mileage;

    @Column(name="engine_displacement")
    private Double engineDisplacement;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "car_status")
    private CarStatus carStatus;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "car_user",
                joinColumns = @JoinColumn(name = "car_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersThatAddedToFavorite;

    public Car(Long id, CarMake make, String model, Integer year, String color, Transmission transmission,
               Fuel fuel, Integer mileage, Double engineDisplacement, CarType carType, Boolean isNew,
               String description, Double price, CarStatus carStatus) {
        this.carId = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.transmission = transmission;
        this.fuel = fuel;
        this.mileage = mileage;
        this.engineDisplacement = engineDisplacement;
        this.carType = carType;
        this.isNew = isNew;
        this.description = description;
        this.price = price;
        this.carStatus = carStatus;
        this.usersThatAddedToFavorite = new ArrayList<>();
    }

    public Car() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public CarMake getMake() {
        return make;
    }

    public void setMake(CarMake make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Double engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public List<User> getUsersThatAddedToFavorite() {
        return usersThatAddedToFavorite;
    }

    public void setUsersThatAddedToFavorite(List<User> usersThatAddedToFavorite) {
        this.usersThatAddedToFavorite = usersThatAddedToFavorite;
    }
}
