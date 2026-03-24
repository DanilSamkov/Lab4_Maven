package org.example;

import org.junit.jupiter.api.Test;

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

        assertNotNull(pants);
        assertNotNull(shirt);

        assertTrue(pants.toString().contains("Штани"));
        assertTrue(shirt.toString().contains("Сорочка"));
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
    }
}
