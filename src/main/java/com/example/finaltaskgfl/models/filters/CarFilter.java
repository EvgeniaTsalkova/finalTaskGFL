package com.example.finaltaskgfl.models.filters;

import com.example.finaltaskgfl.models.Car;
import com.example.finaltaskgfl.models.CarMake;
import com.example.finaltaskgfl.models.enums.CarStatus;
import com.example.finaltaskgfl.models.enums.CarType;
import com.example.finaltaskgfl.models.enums.Fuel;
import com.example.finaltaskgfl.models.enums.Transmission;
import org.springframework.stereotype.Component;

@Component
public class CarFilter extends Car {
    private Integer yearMin;
    private Integer yearMax;
    private Integer mileageMin;
    private Integer mileageMax;
    private Double engineDisplacementMin;
    private Double engineDisplacementMax;
    private Integer priceMin;
    private Integer priceMax;

    public CarFilter(Long id, CarMake make, String model, Integer year, String color, Transmission transmission,
                     Fuel fuel, Integer mileage, Double engineDisplacement, CarType carType, Boolean isNew,
                     String description, Double price, CarStatus carStatus, Integer yearMin, Integer yearMax,
                     Integer mileageMin, Integer mileageMax, Double engineDisplacementMin, Double engineDisplacementMax,
                     Integer priceMin, Integer priceMax) {
        super(id, make, model, year, color, transmission, fuel, mileage, engineDisplacement, carType, isNew,
                description, price, carStatus);
        this.yearMin = yearMin;
        this.yearMax = yearMax;
        this.mileageMin = mileageMin;
        this.mileageMax = mileageMax;
        this.engineDisplacementMin = engineDisplacementMin;
        this.engineDisplacementMax = engineDisplacementMax;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public CarFilter() {
    }

    public Integer getYearMin() {
        return yearMin;
    }

    public void setYearMin(Integer yearMin) {
        this.yearMin = yearMin;
    }

    public Integer getYearMax() {
        return yearMax;
    }

    public void setYearMax(Integer yearMax) {
        this.yearMax = yearMax;
    }

    public Integer getMileageMin() {
        return mileageMin;
    }

    public void setMileageMin(Integer mileageMin) {
        this.mileageMin = mileageMin;
    }

    public Integer getMileageMax() {
        return mileageMax;
    }

    public void setMileageMax(Integer mileageMax) {
        this.mileageMax = mileageMax;
    }

    public Double getEngineDisplacementMin() {
        return engineDisplacementMin;
    }

    public void setEngineDisplacementMin(Double engineDisplacementMin) {
        this.engineDisplacementMin = engineDisplacementMin;
    }

    public Double getEngineDisplacementMax() {
        return engineDisplacementMax;
    }

    public void setEngineDisplacementMax(Double engineDisplacementMax) {
        this.engineDisplacementMax = engineDisplacementMax;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }
}
