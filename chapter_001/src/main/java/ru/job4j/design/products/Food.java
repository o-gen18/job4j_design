package ru.job4j.design.products;

import java.util.Calendar;
import java.util.Objects;

public class Food {
    private String name;
    private Calendar createDate;
    private Calendar expiryDate;
    private double price;
    private String discount;

    public Food(String name, Calendar createDate, Calendar expiryDate, double price, String discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


    /**
     * Calculates the percentage value of food's expiry date.
     * @return int number meaning percents.
     */
    public int getExpiryPercentage() {
        int percent = -1;
        if (!(Calendar.getInstance().getTimeInMillis() >= expiryDate.getTimeInMillis())) {
            percent = (int) (100 * (Calendar.getInstance().getTimeInMillis() - createDate.getTimeInMillis()) /
                    (expiryDate.getTimeInMillis() - createDate.getTimeInMillis()));
        }
        return percent;
    }

    @Override
    public String toString() {
        return "Name - " + name + ", Discount = " + discount;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) && Objects.equals(discount, food.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discount);
    }
}
