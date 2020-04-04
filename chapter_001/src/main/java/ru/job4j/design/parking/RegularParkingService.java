package ru.job4j.design.parking;

import java.util.Set;

public class RegularParkingService implements ParkingService {
    @Override
    public void allocate(Set<Car> cars, Set<Car> trucks, Parking parking) {
        int freeSmallSpace = parking.getSmallCars() - cars.size();
        int freeBigSpace = parking.getTrucks() - trucks.size();
        int extraTrucks = 0;
        if (freeBigSpace >= 0) {
            parking.setTrucks(freeBigSpace);
        } else {
            parking.setTrucks(0);
            extraTrucks = Math.abs(freeBigSpace);
        }

        if (freeSmallSpace <= 0) {
            parking.setSmallCars(0);
        } else if (freeSmallSpace/parking.getN() >= extraTrucks) {
            parking.setSmallCars(freeSmallSpace - parking.getN()*extraTrucks);
        } else {
            parking.setSmallCars(freeSmallSpace);
        }
    }
}
