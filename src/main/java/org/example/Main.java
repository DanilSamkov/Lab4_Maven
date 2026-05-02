package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Головний клас програми (драйвер), що реалізує консольне меню.
 * Забезпечує взаємодію з користувачем та обробку винятків при введенні даних.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Store store = ClothesStorage.loadStore();
        boolean running = true;


        //Головне меню програми
        while (running) {
            System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
            System.out.println("1. Пошук об'єкта");
            System.out.println("2. Створити новий об'єкт (додати одяг)");
            System.out.println("3. Вивести інформацію про всі об'єкти");
            System.out.println("4. Вивести відсортовану інформацію про всі об'єкти");
            System.out.println("5. Скопіювати існуючий об'єкт");
            System.out.println("6. Завершити роботу");
            System.out.print("Оберіть дію (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (store.getItems().isEmpty()) {
                        System.out.println("Магазин порожній! Додайте об'єкти перед тим, як шукати.");
                        break;
                    }

                    System.out.println("\n--- МЕНЮ ПОШУКУ ---");
                    System.out.println("1. За назвою");
                    System.out.println("2. За розміром");
                    System.out.println("3. За ціновим діапазоном");
                    System.out.println("0. Повернутися до головного меню");
                    System.out.print("Оберіть критерій (0-3): ");

                    String searchChoice = scanner.nextLine().trim();
                    List<StoreItem> results = null;

                    switch (searchChoice) {
                        case "1":
                            System.out.print("Введіть назву (або частину назви): ");
                            results = store.searchByName(scanner.nextLine().trim());
                            break;
                        case "2":
                            System.out.print("Введіть розмір (S, M, L, XL, XXL): ");
                            try {
                                results = store.searchBySize(Size.valueOf(scanner.nextLine().trim().toUpperCase()));
                            } catch (IllegalArgumentException e) {
                                System.out.println("Помилка: Невідомий розмір.");
                            }
                            break;
                        case "3":
                            try {
                                System.out.print("Мінімальна ціна: ");
                                double min = Double.parseDouble(scanner.nextLine().trim());
                                System.out.print("Максимальна ціна: ");
                                double max = Double.parseDouble(scanner.nextLine().trim());
                                if (min > max) {
                                    System.out.println("Помилка: мінімум більший за максимум.");
                                } else {
                                    results = store.searchByPriceRange(min, max);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Помилка: Некоректний формат числа.");
                            }
                            break;
                        case "0":
                            System.out.println("Повернення до головного меню...");
                            break;
                        default:
                            System.out.println("Помилка: Некоректний вибір.");
                    }

                    if (results != null) {
                        System.out.println("\n--- Результати пошуку ---");
                        store.printItems(results);
                    }
                    break;
                    
                case "2":
                    System.out.println("\nЯкий саме одяг ви хочете додати?");
                    System.out.println("0 - Повернутися до головного меню (Відміна)");
                    System.out.println("1 - Звичайний одяг");
                    System.out.println("2 - Штани");
                    System.out.println("3 - Сорочка");
                    System.out.println("4 - Шорти");
                    System.out.println("5 - Сорочка-поло");
                    System.out.print("Ваш вибір (0-5): ");
                    String typeChoice = scanner.nextLine().trim();

                    if (typeChoice.equals("0")) {
                        System.out.println("Створення скасовано. Повернення до меню...");
                        break;
                    }

                    if (!typeChoice.matches("[1-5]")) {
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

                        System.out.print("Кількість: ");
                        int quantity = Integer.parseInt(scanner.nextLine().trim());

                        // Спроба створення об'єкта. Якщо дані неправильні, Clothes кине IllegalArgumentException
                        Clothes newItem = null;

                        switch (typeChoice) {
                            case "1":
                                newItem = new BasicClothes(name, sizeEnum, price, color);
                                break;
                            case "2":
                                newItem = new Pants(name, sizeEnum, price, color);
                                break;
                            case "3":
                                newItem = new Shirts(name, sizeEnum, price, color);
                                break;
                            case "4":
                                System.out.print("Ці шорти підходять для плавання? (так/ні): ");
                                String swimInput = scanner.nextLine().trim().toLowerCase();
                                boolean isForSwimming = swimInput.equals("так") || swimInput.equals("yes");
                                newItem = new Shorts(name, sizeEnum, price, color, isForSwimming);
                                break;
                            case "5":
                                System.out.print("Чи є нагрудна кишеня? (так/ні): ");
                                String pocketInput = scanner.nextLine().trim().toLowerCase();
                                boolean hasPocket = pocketInput.equals("так") || pocketInput.equals("yes");
                                newItem = new Polo(name, sizeEnum, price, color, hasPocket);
                                break;
                        }

                        store.addNewClothes(newItem, quantity);
                        System.out.println("Одяг успішно додано!");


                    } catch (NumberFormatException e) {
                        System.out.println("Помилка вводу: Ціна має бути коректним числом!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка збереження даних: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("\n--- Магазин ---");
                    if (store.getItems().isEmpty()) {
                        System.out.println("Список порожній.");
                    } else {
                        store.printItems(store.getItems());
                    }
                    break;

                case "4":
                    System.out.println("\n--- Магазин (Відсортовано за Назвою та Ціною) ---");
                    if (store.getItems().isEmpty()) {
                        System.out.println("Список порожній.");
                    } else {
                        List<StoreItem> sortedList = store.getSortedItems();
                        store.printItems(sortedList);
                    }
                    break;

                case "5":
                    if (store.getItems().isEmpty()) {
                        System.out.println("Магазин порожній! Немає чого копіювати.");
                        break;
                    }

                    System.out.println("\n--- Доступні речі для копіювання ---");

                    store.printItems(store.getItems());

                    System.out.print("Введіть номер речі, яку хочете скопіювати: ");

                    try {
                        int indexToCopy = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        Clothes original = store.getItems().get(indexToCopy).getClothing();

                        // Використання конструктору копіювання
                        Clothes copy = null;
                        if (original instanceof Shorts) {
                            copy = new Shorts((Shorts) original);
                        } else if (original instanceof Pants) { // Перевірка батька після спадкоємця
                            copy = new Pants((Pants) original);
                        } else if (original instanceof Polo) {
                            copy = new Polo((Polo) original);
                        } else if (original instanceof Shirts) { // Перевірка батька після спадкоємця
                            copy = new Shirts((Shirts) original);
                        } else if (original instanceof BasicClothes) {
                            copy = new BasicClothes((BasicClothes) original);
                        }

                        store.addNewClothes(copy, 1);

                        System.out.println("Річ успішно скопійовано!");

                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: введіть коректне число.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Помилка: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Помилка копіювання: " + e.getMessage());
                    }
                    break;

                case "6":
                    ClothesStorage.saveStore(store);
                    System.out.println("Актуальні дані успішно збережено у файл input.json.");
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