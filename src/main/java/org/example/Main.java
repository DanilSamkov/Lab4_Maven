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

        // Запит розміру масиву з використанням try-catch
        while (true) {
            System.out.print("Введіть кількість елементів (одягу) для створення: ");
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

        // Створення масиву об'єктів
        Clothes[] clothes_array = new Clothes[count];
        int currentIndex = 0;
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
                    if (currentIndex >= clothes_array.length) {
                        System.out.println("Помилка: Шафа вже повна! Ви не можете додати більше речей.");
                        break;
                    }

                    System.out.println("\nВведення даних для одягу #" + (currentIndex + 1) + ":");

                    // Блок try-catch для перехоплення помилок валідації з класу Clothes
                    try {
                        System.out.print("Назва: ");
                        String name = scanner.nextLine();

                        System.out.print("Розмір (S, M, L, XL, XXL): ");
                        String size = scanner.nextLine();

                        // Додано запит нового поля color
                        System.out.print("Колір: ");
                        String color = scanner.nextLine();

                        System.out.print("Ціна (використовуйте крапку, наприклад 199.99): ");
                        double price = Double.parseDouble(scanner.nextLine().trim());

                        // Спроба створення об'єкта. Якщо дані неправильні, Clothes кине IllegalArgumentException
                        clothes_array[currentIndex] = new Clothes(name, size, price, color);
                        currentIndex++;
                        System.out.println("Одяг успішно додано!");

                    } catch (NumberFormatException e) {
                        System.out.println("Помилка вводу: Ціна має бути коректним числом!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка збереження даних: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- Ваша шафа ---");
                    if (currentIndex == 0) {
                        System.out.println("Поки що немає жодної речі.");
                    } else {
                        for (int i = 0; i < currentIndex; i++) {
                            System.out.println((i + 1) + ". " + clothes_array[i].toString());
                        }
                    }
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