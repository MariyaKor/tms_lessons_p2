package com.tms.model;

public class Car {
    private String model;
    private String cost;

    public Car(String model, String cost) {
        this.model = model;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Car{" + "model='" + this.model + '\'' + ", cost='" + this.cost + '\'' + '}';
    }

}