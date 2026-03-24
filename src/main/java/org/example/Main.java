package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас програми (драйвер), що реалізує консольне меню.
 * Забезпечує взаємодію з користувачем та обробку винятків при введенні даних.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ArrayList
        List<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        // Головне меню програми
        while (running) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Створити новий об'єкт (додати одяг)");
            System.out.println("2. Вивести інформацію про всі об'єкти");
            System.out.println("3. Скопіювати існуючий об'єкт");
            System.out.println("4. Завершити роботу");
            System.out.print("Оберіть дію (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("\nЯкий саме одяг ви хочете додати?");
                    System.out.println("1 - Звичайний одяг");
                    System.out.println("2 - Штани");
                    System.out.println("3 - Сорочка");
                    System.out.print("Ваш вибір (1-3): ");
                    String typeChoice = scanner.nextLine().trim();

                    if (!typeChoice.equals("1") && !typeChoice.equals("2") && !typeChoice.equals("3")) {
                        System.out.println("Помилка: Невідомий тип одягу. Повернення до головного меню.");
                        break;
                    }

                    System.out.println("\nВведення даних:");

                    // Блок try-catch для перехоплення помилок валідації з класу Clothes
                    try {
                        System.out.print("Назва: ");
                        String name = scanner.nextLine();

                        System.out.print("Розмір (S, M, L, XL, XXL): ");
                        String sizeInput = scanner.nextLine().trim().toUpperCase();

                        Size sizeEnum;
                        try {
                            sizeEnum = Size.valueOf(sizeInput);
                        } catch (IllegalArgumentException ex) {
                            throw new IllegalArgumentException("Некоректний розмір! Дозволені значення: S, M, L, XL, XXL.");
                        }

                        // Додано запит нового поля color
                        System.out.print("Колір: ");
                        String color = scanner.nextLine();

                        System.out.print("Ціна (використовуйте крапку, наприклад 199.99): ");
                        double price = Double.parseDouble(scanner.nextLine().trim());

                        // Спроба створення об'єкта. Якщо дані неправильні, Clothes кине IllegalArgumentException
                        Clothes newItem = null;

                        switch (typeChoice) {
                            case "1":
                                newItem = new Clothes(name, sizeEnum, price, color);
                                break;
                            case "2":
                                newItem = new Pants(name, sizeEnum, price, color);
                                break;
                            case "3":
                                newItem = new Shirts(name, sizeEnum, price, color);
                                break;
                        }

                        clothesList.add(newItem);
                        System.out.println("Одяг успішно додано!");


                    } catch (NumberFormatException e) {
                        System.out.println("Помилка вводу: Ціна має бути коректним числом!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка збереження даних: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- Ваша шафа ---");
                    if (clothesList.isEmpty()) {
                        System.out.println("Список порожній.");
                    } else {
                        for (int i = 0; i < clothesList.size(); i++) {
                            System.out.println((i + 1) + ". " + clothesList.get(i).toString());
                        }
                    }
                    break;

                case "3":
                    if (clothesList.isEmpty()) {
                        System.out.println("Список порожній! Немає чого копіювати.");
                        break;
                    }

                    System.out.println("\n--- Доступні речі для копіювання ---");
                    for (int i = 0; i < clothesList.size(); i++) {
                        System.out.println((i + 1) + ". " + clothesList.get(i).toString());
                    }
                    System.out.print("Введіть номер речі, яку хочете скопіювати: ");

                    try {
                        int indexToCopy = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        Clothes original = clothesList.get(indexToCopy);

                        // Використання конструктору копіювання
                        Clothes copy = new Clothes(original);

                        clothesList.add(copy);

                        System.out.println("Річ успішно скопійовано!");

                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: введіть коректне число.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка копіювання: " + e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("Роботу завершено. До побачення!");
                    running = false;
                    break;

                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть число від 1 до 4.");
            }
        }

        scanner.close();
    }
}