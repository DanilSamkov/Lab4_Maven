package org.example;

import java.util.Comparator;
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

    /**
     * Компаратори
     */
    public static final Comparator<StoreItem> SORT_BY_PRICE = new Comparator<StoreItem>() {
        @Override
        public int compare(StoreItem o1, StoreItem o2) {
            return Double.compare(o1.getClothing().getPrice(), o2.getClothing().getPrice());
        }
    };

    public static final Comparator<StoreItem> SORT_BY_QUANTITY_DESC = new Comparator<StoreItem>() {
        @Override
        public int compare(StoreItem o1, StoreItem o2) {
            return Integer.compare(o2.getQuantity(), o1.getQuantity());
        }
    };

    // 3. За розміром
    public static final Comparator<StoreItem> SORT_BY_SIZE = new Comparator<StoreItem>() {
        @Override
        public int compare(StoreItem o1, StoreItem o2) {
            return o1.getClothing().getSize().compareTo(o2.getClothing().getSize());
        }
    };

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
