package com.example.finaltaskgfl.models;

import com.example.finaltaskgfl.models.enums.CarRequestStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "car_request")
public class CarRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private CarRequestStatus status;

    public CarRequest(Long id, User user, Car car, LocalDate createdAt, CarRequestStatus status) {
        this.requestId = id;
        this.user = user;
        this.car = car;
        this.createdAt = createdAt;
        this.status = status;
    }

    public CarRequest() {
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public CarRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CarRequestStatus status) {
        this.status = status;
    }
}
