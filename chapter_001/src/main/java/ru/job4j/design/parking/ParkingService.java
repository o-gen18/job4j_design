package ru.job4j.design.parking;

import java.util.Set;

public interface ParkingService {
    void allocate(Set<Car> cars, Set<Car> trucks, Parking parking);
}
