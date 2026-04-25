package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class ClothesStorage {
    private static final String FILE_NAME = "input.json";
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Читання з файлу
     */
    public static Store loadStore() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new Store();
        }

        try {
            return mapper.readValue(file, Store.class);
        } catch (IOException e) {
            System.out.println("Помилка читання. Створено новий порожній магазин.");
            return new Store();
        }
    }

    /**
     * Запис у файл
     */
    public static void saveStore(Store store) {
        try {
            mapper.writeValue(new File(FILE_NAME), store);
        } catch (IOException e) {
            System.out.println("Помилка під час збереження файлу: " + e.getMessage());
        }
    }
}
