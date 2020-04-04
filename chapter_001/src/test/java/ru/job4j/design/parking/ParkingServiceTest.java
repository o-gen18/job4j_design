package ru.job4j.design.parking;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParkingServiceTest {
    @Test
    public void when3CarsAnd3TrucksThen9LeftFree() {
        Set<Car> cars = Set.of(new SmallCar(), new SmallCar(), new SmallCar());
        Set<Car> trucks = Set.of(new Truck(), new Truck(), new Truck());
        Parking parking = new CityParking(10, 5, 3);
        ParkingService service = new RegularParkingService();
        service.allocate(cars, trucks, parking);
        Parking expected = new CityParking(7, 2, 3);
        assertThat(parking, is(expected));
    }

    @Test
    public void when4TrucksThenTake2CarSpacesFree() {
        Set<Car> cars = Set.of();
        Set<Car> trucks = Set.of(new Truck(), new Truck(), new Truck(), new Truck());
        Parking parking = new CityParking(10, 2, 2);
        ParkingService service = new RegularParkingService();
        service.allocate(cars, trucks, parking);
        Parking expected = new CityParking(6, 0, 2);
        assertThat(parking, is(expected));
    }
}
