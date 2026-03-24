package org.example;

/**
 * Похідний клас Pants, що успадковується від Clothes.
 */
public class Pants extends Clothes{
    /**
     * Основний конструктор.
     */
    public Pants(String name, Size size, double price, String color) {
        super(name, size, price, color);
    }

    /**
     * Конструктор копіювання.
     */
    public Pants(Pants other) {
        super(other);
    }

    /**
     * Перевизначення методу toString().
     */
    @Override
    public String toString() {
        return "Штани [" + super.toString() + "]";
    }
}
