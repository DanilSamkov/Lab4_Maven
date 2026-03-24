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
            System.out.println("3. Скопіювати існуючий об'єкт");
            System.out.println("4. Завершити роботу");
            System.out.print("Оберіть дію (1-4): ");

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


                    } catch (NumberFormatException e) {
                        System.out.println("Помилка вводу: Ціна має бути коректним числом!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка збереження даних: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.println("\n--- Ваша шафа ---");
                    wardrobe.displayAll();
                    break;

                case "3":
                    if (wardrobe.getCurrentCount() == 0) {
                        System.out.println("Шафа порожня! Немає чого копіювати.");
                        break;
                    }
                    if (wardrobe.isFull()) {
                        System.out.println("Шафа повна! Немає місця для копії.");
                        break;
                    }

                    System.out.println("\n--- Доступні речі для копіювання ---");
                    wardrobe.displayAll();
                    System.out.print("Введіть номер речі, яку хочете скопіювати: ");

                    try {
                        int indexToCopy = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        Clothes original = wardrobe.getClothes(indexToCopy);

                        // Використання конструктору копіювання
                        Clothes copy = new Clothes(original);

                        wardrobe.addClothes(copy);

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