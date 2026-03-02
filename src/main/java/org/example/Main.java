package org.example;

import java.util.Scanner;

/**
 * Клас для тестування роботи з масивом об'єктів Clothes
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;

        // Перевірка введення кількості (має бути ціле число > 0)
        while (true) {
            System.out.print("Введіть кількість елементів (одягу) для створення: ");
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                if (count > 0) {
                    break;
                } else {
                    System.out.println("Помилка: кількість має бути більшою за нуль.");
                }
            } else {
                System.out.println("Помилка: потрібно ввести ціле число (наприклад, 3).");
                scanner.next();
            }
        }

        // Очищення буфера після введення числа
        scanner.nextLine();

        // Створення масиву об'єктів
        Clothes[] clothes_array = new Clothes[count];

        // Заповнення масиву даними з клавіатури
        for (int i = 0; i < count; i++) {
            System.out.println("\nВведення даних для одягу #" + (i + 1) + ":");

            System.out.print("Назва: ");
            String name = scanner.nextLine();

            System.out.print("Розмір (S, M, L тощо): ");
            String size = scanner.nextLine();

            double price = 0;

            // Перевірка введення ціни (має бути число >= 0)
            while (true) {
                System.out.print("Ціна (використовуйте кому, наприклад 199,99): ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price >= 0) {
                        break;
                    } else {
                        System.out.println("Помилка: ціна не може бути від'ємною.");
                    }
                } else {
                    System.out.println("Помилка: введіть коректне число.");
                    scanner.next();
                }
            }

            // Очищення буфера після введення числа
            scanner.nextLine();

            // Ініціалізація об'єкта та запис у масив
            clothes_array[i] = new Clothes(name, size, price);
        }

        // Виведення інформації про всі створені об'єкти
        System.out.println("\n--- Створені об'єкти ---");
        for (Clothes item : clothes_array) {
            System.out.println(item.toString());
        }

        scanner.close();
    }
}