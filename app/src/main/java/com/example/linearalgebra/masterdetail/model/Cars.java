
package com.example.linearalgebra.masterdetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cars {

    @SerializedName("cars")
    @Expose
    private List<Car> cars = null;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars withCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }

}
