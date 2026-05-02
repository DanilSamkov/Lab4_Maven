package org.example;

/**
 * Клас, що містить об'єкт одягу та його кількість
 */
public class StoreItem implements Comparable<StoreItem>{
    private Clothes clothing;
    private int quantity;

    public StoreItem() {}

    public StoreItem(Clothes clothing, int quantity) {
        this.clothing = clothing;
        setQuantity(quantity);
    }

    public Clothes getClothing() {
        return clothing;
    }

    public void setClothing(Clothes clothing) {
        this.clothing = clothing;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість товару не може бути від'ємною.");
        }
        this.quantity = quantity;
    }

    @Override
    public int compareTo(StoreItem other) {
        if (other == null || other.getClothing() == null) return 1;
        if (this.clothing == null) return -1;
        return this.clothing.compareTo(other.getClothing());
    }

    @Override
    public String toString() {
        return clothing.toString() + " | Кількість: " + quantity + " шт.";
    }
}
