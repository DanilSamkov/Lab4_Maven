package org.example;

/**
 * Виняток для некоректних даних об'єкта
 */
public class InvalidClothesDataException extends RuntimeException {
    public InvalidClothesDataException(String message) {
        super(message);
    }
}