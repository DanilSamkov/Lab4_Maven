package org.example;

/**
 * Клас для звичайного одягу,
 * оскільки батьківський клас Clothes тепер абстрактний
 */
public class BasicClothes extends Clothes{

    public BasicClothes() {}

    public BasicClothes(String name, Size size, double price, String color) {
        super(name, size, price, color);
    }

    public BasicClothes(BasicClothes other) {
        super(other);
    }

    @Override
    public String toString() {
        return "Звичайний одяг [" + super.toString() + "]";
    }
}
