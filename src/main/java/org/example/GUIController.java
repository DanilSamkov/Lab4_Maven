package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GUIController {

    @FXML private ComboBox<String> typeBox;
    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<String> sizeBox;
    @FXML private TextField colorField;

    @FXML private CheckBox swimmingCheckBox;
    @FXML private CheckBox pocketCheckBox;

    @FXML private ListView<String> shortInfoListView;

    @FXML private TextField uuidSearchField;
    @FXML private TextArea searchResultArea;

    private Store store;
    private ObservableList<String> listItems;

    @FXML
    public void initialize() {
        store = ClothesStorage.loadStore();
        listItems = FXCollections.observableArrayList();
        shortInfoListView.setItems(listItems);

        typeBox.getItems().addAll("Звичайний одяг", "Штани", "Сорочка", "Шорти", "Сорочка-поло");
        typeBox.setValue("Звичайний одяг");

        sizeBox.getItems().addAll("S", "M", "L", "XL", "XXL");
        sizeBox.setValue("M");

        typeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            swimmingCheckBox.setVisible("Шорти".equals(newValue));
            swimmingCheckBox.setManaged("Шорти".equals(newValue));

            pocketCheckBox.setVisible("Сорочка-поло".equals(newValue));
            pocketCheckBox.setManaged("Сорочка-поло".equals(newValue));
        });

        refreshList();
    }

    @FXML
    public void onAddClick() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Size size = Size.valueOf(sizeBox.getValue());
            String color = colorField.getText();
            String type = typeBox.getValue();

            Clothes newItem = null;

            if (type.equals("Звичайний одяг")) {
                newItem = new BasicClothes(name, size, price, color);
            } else if (type.equals("Штани")) {
                newItem = new Pants(name, size, price, color);
            } else if (type.equals("Сорочка")) {
                newItem = new Shirts(name, size, price, color);
            } else if (type.equals("Шорти")) {
                newItem = new Shorts(name, size, price, color, swimmingCheckBox.isSelected());
            } else if (type.equals("Сорочка-поло")) {
                newItem = new Polo(name, size, price, color, pocketCheckBox.isSelected());
            }

            if (newItem != null) {
                store.addNewClothes(newItem, quantity);
                ClothesStorage.saveStore(store);
                refreshList();

                nameField.clear(); priceField.clear(); quantityField.clear(); colorField.clear();
                swimmingCheckBox.setSelected(false); pocketCheckBox.setSelected(false);

                showAlert(Alert.AlertType.INFORMATION, "Успіх", "Товар успішно додано!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Помилка вводу", "Ціна та кількість мають бути числами!");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Помилка даних", e.getMessage());
        }
    }

    @FXML
    public void onSearchClick() {
        String uuidStr = uuidSearchField.getText();
        StoreItem found = store.searchByUuid(uuidStr);

        if (found != null) {
            searchResultArea.setText("ЗНАЙДЕНО ОБ'ЄКТ:\n\n" + found.toString());
            searchResultArea.setStyle("-fx-text-fill: green;");
        } else {
            searchResultArea.setText("Об'єкт з таким UUID не знайдено або формат некоректний.");
            searchResultArea.setStyle("-fx-text-fill: red;");
        }
    }

    private void refreshList() {
        listItems.clear();
        for (StoreItem item : store.getItems()) {
            Clothes c = item.getClothing();
            listItems.add(c.getName() + " | UUID: " + c.getUuid().toString());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
