package org.example;

/**
 * Виняток коли об'єкт не знайдено в колекції
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
