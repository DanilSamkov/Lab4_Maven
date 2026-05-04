package org.example;

import java.util.Comparator;
import java.util.Objects;
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
            throw new InvalidClothesDataException("Кількість товару не може бути від'ємною.");
        }
        this.quantity = quantity;
    }

    /**
     * Компаратори (лямюда-вирази)
     */
    public static final Comparator<StoreItem> SORT_BY_PRICE =
            (o1, o2) -> Double.compare(o1.getClothing().getPrice(), o2.getClothing().getPrice());

    public static final Comparator<StoreItem> SORT_BY_QUANTITY_DESC =
            (o1, o2) -> Integer.compare(o2.getQuantity(), o1.getQuantity());

    public static final Comparator<StoreItem> SORT_BY_SIZE =
            (o1, o2) -> o1.getClothing().getSize().compareTo(o2.getClothing().getSize());

    @Override
    public int compareTo(StoreItem other) {
        if (other == null || other.getClothing() == null) return 1;
        if (this.clothing == null) return -1;
        return this.clothing.compareTo(other.getClothing());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreItem storeItem = (StoreItem) o;
        return quantity == storeItem.quantity && Objects.equals(clothing, storeItem.clothing);
    }

    @Override
    public String toString() {
        return clothing.toString() + " | Кількість: " + quantity + " шт.";
    }
}
