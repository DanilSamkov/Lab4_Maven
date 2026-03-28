package org.example;

/**
 * Похідний клас 2-го рівня Сорочка-поло.
 */
public class Polo extends Shirts {
    private boolean hasChestPocket;

    /**
     * Основний конструктор.
     */
    public Polo(String name, Size size, double price, String color, boolean hasChestPocket) {
        super(name, size, price, color);
        this.hasChestPocket = hasChestPocket;
    }

    /**
     * Конструктор копіювання.
     */
    public Polo(Polo other) {
        super(other);
        this.hasChestPocket = other.hasChestPocket();
    }

    public boolean hasChestPocket() {
        return hasChestPocket;
    }

    public void setChestPocket(boolean chestPocket) {
        this.hasChestPocket = chestPocket;
    }

    @Override
    public String toString() {
        String pocketInfo;
        if (hasChestPocket) {
            pocketInfo = "З нагрудною кишенею";
        } else {
            pocketInfo = "Без кишені";
        }
        return super.toString() + " Деталі: " + pocketInfo;
    }
}