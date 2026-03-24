package org.example;

public class Wardrobe {
    private Clothes[] items;
    private int currentIndex;

    /**
     * Конструктор створює порожню шафу заданого об'єму.
     */
    public Wardrobe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Об'єм шафи має бути більшим за нуль.");
        }
        this.items = new Clothes[capacity];
        this.currentIndex = 0;
    }

    /**
     * Додає новий одяг до шафи.
     */
    public void addClothes(Clothes clothes) {
        if (clothes == null) {
            throw new IllegalArgumentException("Неможливо додати порожній об'єкт (null).");
        }
        if (isFull()) {
            throw new IllegalStateException("Шафа вже повна! Немає вільного місця.");
        }
        this.items[currentIndex] = clothes;
        this.currentIndex++;
    }

    /**
     * Виводить інформацію про всі речі в шафі.
     */
    public void displayAll() {
        if (currentIndex == 0) {
            System.out.println("Шафа порожня.");
            return;
        }
        for (int i = 0; i < currentIndex; i++) {
            System.out.println((i + 1) + ". " + items[i].toString());
        }
    }

    /**
     * Перевірка на вільне місце.
     */
    public boolean isFull() {
        return currentIndex >= items.length;
    }

    /**
     * Повертає кількість речей у шафі.
     */
    public int getCurrentCount() {
        return currentIndex;
    }

    /**
     * Повертає об'єкт одягу.
     */
    public Clothes getClothes(int index) {
        if (index < 0 || index >= currentIndex) {
            throw new IndexOutOfBoundsException("Некоректний номер речі.");
        }
        return items[index];
    }
}
