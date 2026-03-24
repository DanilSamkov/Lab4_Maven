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
    private static int totalClothes = 0;

    /**
     * Основний конструктор.
     */
    public Clothes(String name, String size, double price, String color) {
        setName(name);
        setSize(size);
        setPrice(price);
        setColor(color);
        totalClothes++;
    }
    /**
     * Конструктор копіювання.
     */
    public Clothes(Clothes other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }

        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
        this.color = other.color;
        totalClothes++;
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
        // Очищаємо від пробілів і переводимо у верхній регістр
        String formattedSize = size.trim().toUpperCase();

        if (!formattedSize.equals("S") &&
                !formattedSize.equals("M") &&
                !formattedSize.equals("L") &&
                !formattedSize.equals("XL") &&
                !formattedSize.equals("XXL")) {

            throw new IllegalArgumentException("Некоректний розмір! Дозволені значення: S, M, L, XL, XXL.");
        }

        this.size = formattedSize;
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

    public static int getTotalClothes() {
        return totalClothes;
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