package ru.job4j.design.parking;

import java.util.Objects;

public class CityParking implements Parking {
    private int smallCars;
    private int trucks;
    private int n;

    public CityParking(int smallCars, int trucks, int n) {
        this.smallCars = smallCars;
        this.trucks = trucks;
        this.n = n;
    }

    public int getSmallCars() {
        return smallCars;
    }

    public void setSmallCars(int smallCars) {
        this.smallCars = smallCars;
    }

    public int getTrucks() {
        return trucks;
    }

    public void setTrucks(int trucks) {
        this.trucks = trucks;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CityParking parking = (CityParking) o;
        return Objects.equals(smallCars, parking.smallCars)
                && Objects.equals(trucks, parking.trucks)
                && Objects.equals(n, parking.n);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smallCars, trucks, n);
    }
}
