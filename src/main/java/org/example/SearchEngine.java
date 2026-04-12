package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що містить методи для пошуку об'єктів у колекції
 */
public class SearchEngine {
    /**
     * Пошук за назвою
     */
    public static List<Clothes> searchByName(List<Clothes> list, String nameQuery) {
        List<Clothes> resultList = new ArrayList<>();
        String queryLower = nameQuery.toLowerCase();

        for (Clothes item : list) {
            if (item.getName().toLowerCase().contains(queryLower)) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    /**
     * Пошук за розміром
     */
    public static List<Clothes> searchBySize(List<Clothes> list, Size targetSize) {
        List<Clothes> resultList = new ArrayList<>();

        for (Clothes item : list) {
            if (item.getSize() == targetSize) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    /**
     * Пошук за ціновим діапазоном
     */
    public static List<Clothes> searchByPriceRange(List<Clothes> list, double minPrice, double maxPrice) {
        List<Clothes> resultList = new ArrayList<>();

        for (Clothes item : list) {
            if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    /**
     * Метод для виведення результатів пошуку
     */
    public static void printSearchResults(List<Clothes> results) {
        System.out.println("\n--- Результати пошуку ---");
        if (results.isEmpty()) {
            System.out.println("Жоден об'єкт не відповідає умовам пошуку.");
        } else {
            System.out.println("Знайдено об'єктів: " + results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).toString());
            }
        }
    }
}
