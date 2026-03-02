package org.example;

import java.util.Objects;

/**
 * Клас, що описує одяг
 */
public class Clothes {
    private String name;
    private String size;
    private double price;

    // Конструктор з параметрами
    public Clothes(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // Гетери та сетери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "Clothes{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }

    // Метод equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return Double.compare(clothes.price, price) == 0 &&
                Objects.equals(name, clothes.name) &&
                Objects.equals(size, clothes.size);
    }
}