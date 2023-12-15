package com.example.finaltaskgfl.specifications;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.CarMake;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.enums.CarType;
import com.example.finaltaskgfl.models.enums.Fuel;
import com.example.finaltaskgfl.models.enums.Transmission;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecifications {

    public static Specification<Car> makeEquals(CarMake make) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("make"), make);
    }

    public static Specification<Car> modelEquals(String model) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("model"), model);
    }

    public static Specification<Car> colorEquals(String color) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Car> transmissionEquals(Transmission transmission) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transmission"), transmission);
    }

    public static Specification<Car> fuelEquals(Fuel fuel) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("fuel"), fuel);
    }

    public static Specification<Car> carTypeEquals(CarType carType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("carType"), carType);
    }

    public static Specification<Car> isNewEquals(Boolean isNew) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isNew"), isNew);
    }

    public static Specification<Car> statusEquals(CarStatus carStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("carStatus"), carStatus);
    }

    public static Specification<Car> priceBetween(Integer priceMin, Integer priceMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), priceMin, priceMax);
    }

    public static Specification<Car> yearBetween(Integer yearMin, Integer yearMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("year"), yearMin, yearMax);
    }

    public static Specification<Car> mileageBetween(Integer mileageMin, Integer mileageMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("mileage"), mileageMin, mileageMax);
    }

    public static Specification<Car> engineDisplacementBetween(Double engineMin, Double engineMax) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("engineDisplacement"), engineMin, engineMax);
    }
}
