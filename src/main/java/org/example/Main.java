package org.example;

import java.util.Scanner;

/**
 * Головний клас програми (драйвер), що реалізує консольне меню.
 * Забезпечує взаємодію з користувачем та обробку винятків при введенні даних.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        // Запит розміру шафи
        while (true) {
            System.out.print("Введіть кількість елементів одягу для створення (об'єм шафи): ");
            try {
                count = Integer.parseInt(scanner.nextLine().trim());
                if (count > 0) {
                    break;
                } else {
                    System.out.println("Помилка: кількість має бути більшою за нуль.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число (наприклад, 3).");
            }
        }

        // Створення шафи
        Wardrobe wardrobe = new Wardrobe(count);
        boolean running = true;

        // Головне меню програми
        while (running) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Створити новий об'єкт (додати одяг)");
            System.out.println("2. Вивести інформацію про всі об'єкти");
            System.out.println("3. Завершити роботу");
            System.out.print("Оберіть дію (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (wardrobe.isFull()) {
                        System.out.println("Помилка: Шафа вже повна! Ви не можете додати більше речей.");
                        break;
                    }

                    System.out.println("\nВведення даних для одягу:");

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
                        Clothes newItem = new Clothes(name, sizeEnum, price, color);
                        // АГРЕГАЦІЯ: Передаємо готовий об'єкт у шафу
                        wardrobe.addClothes(newItem);

                        System.out.println("Одяг успішно додано!");

                        System.out.println("Статистика: всього створено об'єктів Clothes: " + Clothes.getTotalClothes());

                    } catch (NumberFormatException e) {
                        System.out.println("Помилка вводу: Ціна має бути коректним числом!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка збереження даних: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- Ваша шафа ---");
                    wardrobe.displayAll();
                    System.out.println("Загальна кількість створених об'єктів Clothes: " + Clothes.getTotalClothes());
                    break;

                case "3":
                    System.out.println("Роботу завершено. До побачення!");
                    running = false;
                    break;

                default:
                    System.out.println("Помилка: Некоректний вибір. Введіть 1, 2 або 3.");
            }
        }

        scanner.close();
    }
}