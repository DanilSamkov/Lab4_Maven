package org.example;

/**
 * Похідний клас Shirts, що успадковується від базового Clothes.
 */
public class Shirts extends Clothes{
    /**
     * Основний конструктор.
     */
    public Shirts(String name, Size size, double price, String color) {
        super(name, size, price, color);
    }

    /**
     * Конструктор копіювання.
     */
    public Shirts(Shirts other) {
        super(other);
    }

    /**
     * Перевизначення методу toString().
     */
    @Override
    public String toString() {
        return "Сорочка [" + super.toString() + "]";
    }
}
