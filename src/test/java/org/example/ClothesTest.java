package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClothesTest {

    @Test
    void succesfullClothCreation(){

        Clothes item = new Clothes("Футболка", Size.XXL, 250.50, "Чорний");

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
        Clothes item = new Clothes("Джинси", Size.S, 900.25, "Синій");

        assertThrows(IllegalArgumentException.class,()->{item.setName("");});
        assertThrows(IllegalArgumentException.class,()->{item.setColor("");});
        assertThrows(IllegalArgumentException.class,()->{item.setSize(null);});
        assertThrows(IllegalArgumentException.class,()->{item.setPrice(-1);});

        assertThrows(IllegalArgumentException.class,()->{new Clothes("",Size.M,456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх",null,456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх",Size.M,-546,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх",Size.M,456.45,"");});
    }

    @Test
    void copyConstructorTest() {
        Clothes original = new Clothes("Светр", Size.L, 850.0, "Сірий");
        Clothes copy = new Clothes(original);

        assertNotNull(copy);
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getSize(), copy.getSize());
        assertEquals(original.getPrice(), copy.getPrice());
        assertEquals(original.getColor(), copy.getColor());

        assertThrows(IllegalArgumentException.class,()->{new Clothes(null);});
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
        List<Clothes> originalList = new ArrayList<>();
        originalList.add(new Clothes("Куртка", Size.XL, 2500.0, "Чорний"));
        originalList.add(new Shorts("Гавайські", Size.L, 450.0, "Червоний", true));
        originalList.add(new Polo("Спортивне", Size.S, 650.0, "Зелений", false));

        ClothesStorage.saveClothes(originalList);

        List<Clothes> loadedList = ClothesStorage.loadClothes();

        assertNotNull(loadedList);
        assertEquals(originalList.size(), loadedList.size());

        assertSame(loadedList.get(0).getClass(), Clothes.class);
        assertSame(loadedList.get(1).getClass(), Shorts.class);
        assertSame(loadedList.get(2).getClass(), Polo.class);

        Shorts loadedShorts = (Shorts) loadedList.get(1);
        assertEquals("Гавайські", loadedShorts.getName());
        assertTrue(loadedShorts.isForSwimming());

        Polo loadedPolo = (Polo) loadedList.get(2);
        assertEquals("Спортивне", loadedPolo.getName());
        assertTrue(loadedPolo.toString().contains("Без кишені"));
    }

    private List<Clothes> getTestCollection() {
        List<Clothes> list = new ArrayList<>();
        list.add(new Clothes("Футболка біла", Size.M, 300.0, "Білий"));
        list.add(new Clothes("Футболка чорна", Size.L, 350.0, "Чорний"));
        list.add(new Pants("Джинси", Size.M, 1200.0, "Синій"));
        list.add(new Shorts("Шорти пляжні", Size.S, 450.0, "Червоний", true));
        list.add(new Polo("Поло", Size.XL, 800.0, "Зелений", false));
        return list;
    }

    @Test
    void searchByNameTest() {
        List<Clothes> testList = getTestCollection();

        List<Clothes> resultMultiple = SearchEngine.searchByName(testList, "Футболка");
        assertEquals(2, resultMultiple.size());

        List<Clothes> resultCaseInsensitive = SearchEngine.searchByName(testList, "ДЖИНСИ");
        assertEquals(1, resultCaseInsensitive.size());
        assertEquals("Джинси", resultCaseInsensitive.get(0).getName());

        List<Clothes> resultEmpty = SearchEngine.searchByName(testList, "Капелюх");
        assertTrue(resultEmpty.isEmpty());
    }

    @Test
    void searchBySizeTest() {
        List<Clothes> testList = getTestCollection();

        List<Clothes> resultSizeM = SearchEngine.searchBySize(testList, Size.M);
        assertEquals(2, resultSizeM.size());

        List<Clothes> resultSizeXL = SearchEngine.searchBySize(testList, Size.XL);
        assertEquals(1, resultSizeXL.size());
        assertEquals("Поло", resultSizeXL.get(0).getName());

        List<Clothes> resultSizeXXL = SearchEngine.searchBySize(testList, Size.XXL);
        assertTrue(resultSizeXXL.isEmpty());
    }

    @Test
    void searchByPriceRangeTest() {
        List<Clothes> testList = getTestCollection();

        List<Clothes> resultRange = SearchEngine.searchByPriceRange(testList, 300.0, 500.0);
        assertEquals(3, resultRange.size()); // 300, 350, 450

        List<Clothes> resultExact = SearchEngine.searchByPriceRange(testList, 1200.0, 1200.0);
        assertEquals(1, resultExact.size());
        assertEquals("Джинси", resultExact.get(0).getName());

        List<Clothes> resultEmpty = SearchEngine.searchByPriceRange(testList, 5000.0, 10000.0);
        assertTrue(resultEmpty.isEmpty());
    }
}
