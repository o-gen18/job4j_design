package ru.job4j.design.parking;

import java.util.Set;

public class RegularParkingService implements ParkingService {
    private Set<Car> cars;
    private Set<Car> trucks;
    private Parking parking;

    public RegularParkingService(Set<Car> cars, Set<Car> trucks, Parking parking) {
        this.cars = cars;
        this.trucks = trucks;
        this.parking = parking;
    }

    @Override
    public void allocate() {
    }
}
