package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClothesTest {

    @Test
    void succesfullClothCreation(){

        Clothes item = new BasicClothes("Футболка", Size.XXL, 250.50, "Чорний");

        assertNotNull(item);

        assertEquals("Футболка", item.getName());
        assertEquals(Size.XXL, item.getSize());
        assertEquals("Чорний", item.getColor());
        assertEquals(250.50, item.getPrice());

        item.setPrice(450.0);
        item.setSize(Size.XL);

        assertEquals(450.0, item.getPrice());
        assertEquals(Size.XL, item.getSize());
    }

    @Test
    void setIllegalExceptionsTest(){
        Clothes item = new BasicClothes("Джинси", Size.S, 900.25, "Синій");

        assertThrows(IllegalArgumentException.class,()->{item.setName("");});
        assertThrows(IllegalArgumentException.class,()->{item.setColor("");});
        assertThrows(IllegalArgumentException.class,()->{item.setSize(null);});
        assertThrows(IllegalArgumentException.class,()->{item.setPrice(-1);});

        assertThrows(IllegalArgumentException.class,()->{new BasicClothes("",Size.M,456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new BasicClothes("Капелюх",null,456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new BasicClothes("Капелюх",Size.M,-546,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new BasicClothes("Капелюх",Size.M,456.45,"");});
    }

    @Test
    void copyConstructorTest() {
        BasicClothes original = new BasicClothes("Светр", Size.L, 850.0, "Сірий");
        BasicClothes copy = new BasicClothes(original);

        assertNotNull(copy);
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getSize(), copy.getSize());
        assertEquals(original.getPrice(), copy.getPrice());
        assertEquals(original.getColor(), copy.getColor());

        assertThrows(IllegalArgumentException.class,()->{new BasicClothes(null);});
    }

    @Test
    void polymorphismTest() {
        Clothes pants = new Pants("Брюки", Size.M, 1500.0, "Чорний");
        Clothes shirt = new Shirts("Оксфорд", Size.L, 1200.0, "Білий");

        Clothes shorts = new Shorts("Пляжні", Size.M, 600.0, "Жовтий", true);
        Clothes polo = new Polo("Кежуал", Size.L, 800.0, "Синій", false);

        assertNotNull(pants);
        assertNotNull(shirt);

        assertNotNull(shorts);
        assertNotNull(polo);

        assertTrue(pants.toString().contains("Штани"));
        assertTrue(shirt.toString().contains("Сорочка"));

        assertTrue(shorts.toString().contains("Пляжні (для плавання)"));
        assertTrue(polo.toString().contains("Без кишені"));
    }

    @Test
    void DerivedClassesCopyConstructorsTest() {
        Pants originalPants = new Pants("Карго", Size.L, 1200.0, "Оливковий");
        Pants copiedPants = new Pants(originalPants);

        assertEquals(originalPants.getName(), copiedPants.getName());
        assertTrue(copiedPants.toString().contains("Штани"));

        Shirts originalShirt = new Shirts("Поло", Size.S, 900.0, "Блакитний");
        Shirts copiedShirt = new Shirts(originalShirt);

        assertEquals(originalShirt.getName(), copiedShirt.getName());
        assertTrue(copiedShirt.toString().contains("Сорочка"));

        Shorts originalShorts = new Shorts("Бермуди", Size.M, 450.0, "Хакі", true);
        Shorts copiedShorts = new Shorts(originalShorts);
        assertEquals(originalShorts.isForSwimming(), copiedShorts.isForSwimming());

        Polo originalPolo = new Polo("Теніска", Size.L, 750.0, "Білий", true);
        Polo copiedPolo = new Polo(originalPolo);
        assertTrue(copiedPolo.toString().contains("З нагрудною кишенею"));
    }

    @Test
    void jsonStorageTest() {
        Store store = new Store();
        store.addNewClothes(new BasicClothes("Куртка", Size.XL, 2500.0, "Чорний"), 2);
        store.addNewClothes(new Shorts("Гавайські", Size.L, 450.0, "Червоний", true), 5);
        store.addNewClothes(new Polo("Спортивне", Size.S, 650.0, "Зелений", false), 3);

        ClothesStorage.saveStore(store);
        Store loadedStore = ClothesStorage.loadStore();

        assertNotNull(loadedStore);
        assertEquals(3, loadedStore.getItems().size());

        assertSame(loadedStore.getItems().get(0).getClothing().getClass(), BasicClothes.class);
        assertSame(loadedStore.getItems().get(1).getClothing().getClass(), Shorts.class);
        assertSame(loadedStore.getItems().get(2).getClothing().getClass(), Polo.class);

        Shorts loadedShorts = (Shorts) loadedStore.getItems().get(1).getClothing();
        assertEquals("Гавайські", loadedShorts.getName());
        assertTrue(loadedShorts.isForSwimming());
        assertEquals(5, loadedStore.getItems().get(1).getQuantity());

        Polo loadedPolo = (Polo) loadedStore.getItems().get(2).getClothing();
        assertEquals("Спортивне", loadedPolo.getName());
        assertTrue(loadedPolo.toString().contains("Без кишені"));
        assertEquals(3, loadedStore.getItems().get(2).getQuantity());
    }

    private Store getTestStore() {
        Store store = new Store();
        store.addNewClothes(new BasicClothes("Футболка біла", Size.M, 300.0, "Білий"), 10);
        store.addNewClothes(new BasicClothes("Футболка чорна", Size.L, 350.0, "Чорний"), 5);
        store.addNewClothes(new Pants("Джинси", Size.M, 1200.0, "Синій"), 7);
        store.addNewClothes(new Shorts("Шорти пляжні", Size.S, 450.0, "Червоний", true), 3);
        store.addNewClothes(new Polo("Поло", Size.XL, 800.0, "Зелений", false), 2);
        return store;
    }

    @Test
    void searchByNameTest() {
        Store store = getTestStore();

        List<StoreItem> resultMultiple = store.searchByName("Футболка");
        assertEquals(2, resultMultiple.size());

        List<StoreItem> resultCaseInsensitive = store.searchByName("ДЖИНСИ");
        assertEquals(1, resultCaseInsensitive.size());
        assertEquals("Джинси", resultCaseInsensitive.get(0).getClothing().getName());

        List<StoreItem> resultEmpty = store.searchByName("Капелюх");
        assertTrue(resultEmpty.isEmpty());
    }

    @Test
    void searchBySizeTest() {
        Store store = getTestStore();

        List<StoreItem> resultSizeM = store.searchBySize(Size.M);
        assertEquals(2, resultSizeM.size());

        List<StoreItem> resultSizeXL = store.searchBySize(Size.XL);
        assertEquals(1, resultSizeXL.size());
        assertEquals("Поло", resultSizeXL.get(0).getClothing().getName());

        List<StoreItem> resultSizeXXL = store.searchBySize(Size.XXL);
        assertTrue(resultSizeXXL.isEmpty());
    }

    @Test
    void searchByPriceRangeTest() {
        Store store = getTestStore();

        List<StoreItem> resultRange = store.searchByPriceRange(300.0, 500.0);
        assertEquals(3, resultRange.size()); // 300, 350, 450

        List<StoreItem> resultExact = store.searchByPriceRange(1200.0, 1200.0);
        assertEquals(1, resultExact.size());
        assertEquals("Джинси", resultExact.get(0).getClothing().getName());

        List<StoreItem> resultEmpty = store.searchByPriceRange(5000.0, 10000.0);
        assertTrue(resultEmpty.isEmpty());
    }

    @Test
    void storeAggregationTest() {
        Store store = new Store();
        Clothes shirt1 = new BasicClothes("Худі", Size.L, 1000.0, "Чорний");
        Clothes shirt2 = new BasicClothes("Худі", Size.L, 1000.0, "Чорний");
        Pants pants = new Pants("Спортивки", Size.M, 800.0, "Сірий");

        store.addNewClothes(shirt1, 5);
        assertEquals(1, store.getItems().size());
        assertEquals(5, store.getItems().get(0).getQuantity());

        store.addNewClothes(shirt2, 3);
        assertEquals(1, store.getItems().size());
        assertEquals(8, store.getItems().get(0).getQuantity());

        store.addNewClothes(pants, 2);
        assertEquals(2, store.getItems().size());
        assertEquals(2, store.getItems().get(1).getQuantity());
    }

    @Test
    void sortingTest() {
        Store store = new Store();

        store.addNewClothes(new BasicClothes("Футболка", Size.M, 300.0, "Білий"), 1);
        store.addNewClothes(new Pants("Джинси", Size.L, 1200.0, "Синій"), 1);
        store.addNewClothes(new BasicClothes("Анорак", Size.S, 1500.0, "Чорний"), 1);
        store.addNewClothes(new BasicClothes("Анорак", Size.S, 1200.0, "Білий"), 1);

        List<StoreItem> sorted = store.getSortedItems();

        assertEquals("Анорак", sorted.get(0).getClothing().getName());
        assertEquals(1200.0, sorted.get(0).getClothing().getPrice());

        assertEquals("Анорак", sorted.get(1).getClothing().getName());
        assertEquals(1500.0, sorted.get(1).getClothing().getPrice());

        assertEquals("Джинси", sorted.get(2).getClothing().getName());
        assertEquals("Футболка", sorted.get(3).getClothing().getName());
    }

    @Test
    void comparatorSortingTest() {
        Store store = new Store();
        store.addNewClothes(new BasicClothes("Футболка", Size.L, 500.0, "Білий"), 5);
        store.addNewClothes(new BasicClothes("Шкарпетки", Size.S, 100.0, "Чорний"), 50);
        store.addNewClothes(new BasicClothes("Світшот", Size.XXL, 1200.0, "Сірий"), 2);

        List<StoreItem> sortedByPrice = store.getSortedItems(StoreItem.SORT_BY_PRICE);
        assertEquals("Шкарпетки", sortedByPrice.get(0).getClothing().getName()); // 100.0
        assertEquals("Світшот", sortedByPrice.get(2).getClothing().getName());   // 1200.0

        List<StoreItem> sortedByQuantity = store.getSortedItems(StoreItem.SORT_BY_QUANTITY_DESC);
        assertEquals("Шкарпетки", sortedByQuantity.get(0).getClothing().getName()); // 50 шт
        assertEquals("Світшот", sortedByQuantity.get(2).getClothing().getName());   // 2 шт

        List<StoreItem> sortedBySize = store.getSortedItems(StoreItem.SORT_BY_SIZE);
        assertEquals(Size.S, sortedBySize.get(0).getClothing().getSize());
        assertEquals(Size.XXL, sortedBySize.get(2).getClothing().getSize());
    }

    @Test
    void updateAndDeleteTest() {
        Store store = new Store();
        BasicClothes originalClothes = new BasicClothes("Кепка", Size.M, 300.0, "Червоний");
        store.addNewClothes(originalClothes, 10);

        StoreItem existingItem = store.getItems().get(0);

        BasicClothes modifiedClothes = new BasicClothes("Кепка", Size.L, 350.0, "Чорний");
        StoreItem newItem = new StoreItem(modifiedClothes, 15);

        boolean updateResult = store.update(existingItem, newItem);

        assertTrue(updateResult);
        assertEquals(Size.L, store.getItems().get(0).getClothing().getSize());
        assertEquals(350.0, store.getItems().get(0).getClothing().getPrice());
        assertEquals(15, store.getItems().get(0).getQuantity());

        boolean deleteResult = store.delete(newItem);

        assertTrue(deleteResult);
        assertTrue(store.getItems().isEmpty());

        assertFalse(store.delete(newItem));
    }
}
