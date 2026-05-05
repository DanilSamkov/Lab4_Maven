package org.example;

import java.util.Objects;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Абстрактний батьківський клас, що описує одяг.
 * Містить перевірку вхідних даних (валідацію).
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicClothes.class, name = "clothes"),
        @JsonSubTypes.Type(value = Pants.class, name = "pants"),
        @JsonSubTypes.Type(value = Shirts.class, name = "shirts"),
        @JsonSubTypes.Type(value = Shorts.class, name = "shorts"),
        @JsonSubTypes.Type(value = Polo.class, name = "polo")
})
public abstract class Clothes implements Comparable<Clothes>, Identifiable{
    private UUID uuid;
    private String name;
    private Size size;
    private double price;
    private String color;

    /**
     * Порожній конструктор
     */
    public Clothes() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Основний конструктор.
     */
    public Clothes(String name, Size size, double price, String color) {
        this.uuid = UUID.randomUUID();
        setName(name);
        setSize(size);
        setPrice(price);
        setColor(color);
    }
    /**
     * Конструктор копіювання.
     */
    public Clothes(Clothes other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }
        this.uuid = UUID.randomUUID();
        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
        this.color = other.color;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Comparable
     */
    @Override
    public int compareTo(Clothes other) {
        if (other == null) return 1;

        int nameCompare = this.name.compareToIgnoreCase(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }

        return Double.compare(this.price, other.price);
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

    public Size getSize() {
        return size;
    }
    /**
     * Встановлення розміру (enum)
     */
    public void setSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("Розмір не може бути порожнім (null).");
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
                "UUID=" + uuid +
                ", name='" + name + '\'' +
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