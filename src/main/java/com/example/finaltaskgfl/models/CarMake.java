package com.example.finaltaskgfl.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="car_make")
public class CarMake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_id", nullable = false)
    private Long makeId;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "make", cascade = CascadeType.ALL)
    private List<Car> cars;

    public CarMake(Long makeId, String name, List<Car> cars) {
        this.makeId = makeId;
        this.name = name;
        this.cars = cars;
    }

    public CarMake() {
    }

    public Long getMakeId() {
        return makeId;
    }

    public void setMakeId(Long makeId) {
        this.makeId = makeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
