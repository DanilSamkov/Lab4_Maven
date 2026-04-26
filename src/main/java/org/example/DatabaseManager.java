package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * Клас для роботи з базою даних
 */
public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            System.out.println("Конфігурацію БД успішно завантажено.");
        } catch (IOException e) {
            System.out.println("Помилка читання файлу конфігурації БД: " + e.getMessage());
        }
    }

    /**
     * Збереження об'єкта в базу даних
     */
    public void saveItem(Clothes clothes, int quantity) {
        if (url == null || user == null || password == null) {
            System.out.println("БД не налаштована. Пропуск збереження в БД.");
            return;
        }

        String sql = "INSERT INTO store_items (type, name, size, price, color, quantity, is_for_swimming, has_chest_pocket) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String type = "clothes";
            if (clothes instanceof Shorts) type = "shorts";
            else if (clothes instanceof Pants) type = "pants";
            else if (clothes instanceof Polo) type = "polo";
            else if (clothes instanceof Shirts) type = "shirts";

            pstmt.setString(1, type);
            pstmt.setString(2, clothes.getName());
            pstmt.setString(3, clothes.getSize().name());
            pstmt.setDouble(4, clothes.getPrice());
            pstmt.setString(5, clothes.getColor());
            pstmt.setInt(6, quantity);

            if (clothes instanceof Shorts) {
                pstmt.setBoolean(7, ((Shorts) clothes).isForSwimming());
            } else {
                pstmt.setNull(7, Types.BOOLEAN);
            }

            if (clothes instanceof Polo) {
                pstmt.setBoolean(8, ((Polo) clothes).isChestPocket());
            } else {
                pstmt.setNull(8, Types.BOOLEAN);
            }

            pstmt.executeUpdate();
            System.out.println("[БД] Об'єкт успішно збережено у базу даних PostgreSQL.");

        } catch (SQLException e) {
            System.out.println("[БД Помилка] Не вдалося зберегти об'єкт: " + e.getMessage());
        }
    }
}
