package org.example;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Клас, який володіє колекцією товарів
 */
public class Store {
    private List<StoreItem> items;

    public Store() {
        this.items = new ArrayList<>();
    }

    public List<StoreItem> getItems() {
        return items;
    }

    public void setItems(List<StoreItem> items) {
        this.items = items;
    }

    /**
     * Додавання нового одягу
     */
    public void addNewClothes(Clothes cl, int quantity) {
        if (cl == null) {
            throw new IllegalArgumentException("Дані про одяг не можуть бути порожніми.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Введіть коректну кількість товару для додавання (більше 0).");
        }

        for (StoreItem item : items) {
            if (item.getClothing().equals(cl)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new StoreItem(cl, quantity));
    }

    /**
     * Методи пошуку
     */
    public List<StoreItem> searchByName(String nameQuery) {
        List<StoreItem> resultList = new ArrayList<>();
        String queryLower = nameQuery.toLowerCase();

        for (StoreItem item : items) {
            if (item.getClothing().getName().toLowerCase().contains(queryLower)) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public List<StoreItem> searchBySize(Size targetSize) {
        List<StoreItem> resultList = new ArrayList<>();

        for (StoreItem item : items) {
            if (item.getClothing().getSize() == targetSize) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    public List<StoreItem> searchByPriceRange(double minPrice, double maxPrice) {
        List<StoreItem> resultList = new ArrayList<>();

        for (StoreItem item : items) {
            double price = item.getClothing().getPrice();
            if (price >= minPrice && price <= maxPrice) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    /**
     * Виведення списку товарів
     */
    public void printItems(List<StoreItem> listToPrint) {
        if (listToPrint == null || listToPrint.isEmpty()) {
            System.out.println("Список порожній або нічого не знайдено.");
        } else {
            System.out.println("Кількість позицій: " + listToPrint.size());
            for (int i = 0; i < listToPrint.size(); i++) {
                System.out.println((i + 1) + ". " + listToPrint.get(i).toString());
            }
        }
    }

    /**
     * Повертає новий відсортований список товарів. ЛР№13
     */
    @JsonIgnore
    public List<StoreItem> getSortedItems() {
        List<StoreItem> sortedList = new ArrayList<>(this.items);
        java.util.Collections.sort(sortedList);
        return sortedList;
    }

    /**
     * Повертає новий відсортований список товарів за заданим критерієм (Comparator). ЛР№14
     */
    @JsonIgnore
    public List<StoreItem> getSortedItems(java.util.Comparator<StoreItem> comparator) {
        List<StoreItem> sortedList = new ArrayList<>(this.items);
        java.util.Collections.sort(sortedList, comparator);
        return sortedList;
    }

    /**
     * Update
     */
    public void update(StoreItem existingObject, StoreItem newObject) {
        if (existingObject == null || newObject == null) {
            throw new InvalidClothesDataException("Об'єкти не можуть бути null.");
        }

        int index = items.indexOf(existingObject);
        if (index != -1) {
            items.set(index, newObject);
        } else {
            throw new ItemNotFoundException("Помилка: Об'єкт для оновлення не знайдено на складі!");
        }
    }

    /**
     * Delete
     */
    public void delete(StoreItem existingObject) {
        if (existingObject == null) {
            throw new InvalidClothesDataException("Об'єкт не може бути null.");
        }

        if (!items.remove(existingObject)) {
            throw new ItemNotFoundException("Помилка: Об'єкт для видалення не знайдено на складі!");
        }
    }
}
