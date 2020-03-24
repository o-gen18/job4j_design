package ru.job4j.design.parking;

public class CityParking implements Parking {
    private int smallCars;
    private int trucks;
    private int n;

    public CityParking(int smallCars, int trucks, int n) {
        this.smallCars = smallCars;
        this.trucks = trucks;
        this.n = n;
    }
}
