package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClothesStorage {
    private static final String FILE_NAME = "input.json";
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Читання з файлу
     */
    public static List<Clothes> loadClothes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(file, new TypeReference<List<Clothes>>() {});
        } catch (IOException e) {
            System.out.println("Помилка під час читання файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Запис у файл
     */
    public static void saveClothes(List<Clothes> clothesList) {
        try {
            mapper.writerFor(new TypeReference<List<Clothes>>() {})
                    .writeValue(new File(FILE_NAME), clothesList);

        } catch (IOException e) {
            System.out.println("Помилка під час збереження файлу: " + e.getMessage());
        }
    }
}
