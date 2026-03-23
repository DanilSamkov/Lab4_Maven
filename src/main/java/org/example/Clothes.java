package org.example;

import java.util.Objects;

/**
 * Клас, що описує одяг.
 * Містить перевірку вхідних даних (валідацію).
 */
public class Clothes {
    private String name;
    private String size;
    private double price;
    private String color;

    // Конструктор з параметрами
    public Clothes(String name, String size, double price, String color) {
        setName(name);
        setSize(size);
        setPrice(price);
        setColor(color);
    }

    // Гетери та сетери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва не може бути порожньою.");
        }
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Розмір не може бути порожнім.");
        }
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною.");
        }
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Колір не може бути порожнім.");
        }
        this.color = color;
    }

    // Метод toString()
    @Override
    public String toString() {
        return "Clothes{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
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
                Objects.equals(size, clothes.size) &&
                Objects.equals(color, clothes.color);
    }
}