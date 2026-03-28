package org.example;

/**
 * Похідний клас 2-го рівня Шорти.
 */
public class Shorts extends Pants {
    private boolean isForSwimming;

    /**
     * Основний конструктор.
     */
    public Shorts(String name, Size size, double price, String color, boolean isForSwimming) {
        super(name, size, price, color);
        this.isForSwimming = isForSwimming;
    }

    /**
     * Конструктор копіювання.
     */
    public Shorts(Shorts other) {
        super(other);
        this.isForSwimming = other.isForSwimming();
    }

    public boolean isForSwimming() {
        return isForSwimming;
    }

    public void setForSwimming(boolean forSwimming) {
        this.isForSwimming = forSwimming;
    }

    @Override
    public String toString() {
        String type;
        if (isForSwimming) {
            type = "Пляжні (для плавання)";
        } else {
            type = "Повсякденні";
        }
        return super.toString() + " Тип: " + type;
    }
}
