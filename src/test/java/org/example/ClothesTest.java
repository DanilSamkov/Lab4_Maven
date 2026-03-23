package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClothesTest {

    @Test
    void succesfullClothCreation(){

        Clothes item = new Clothes("Футболка", "XXL", 250.50, "Чорний");

        assertNotNull(item);

        assertEquals("Футболка", item.getName());
        assertEquals("XXL", item.getSize());
        assertEquals("Чорний", item.getColor());
        assertEquals(250.50, item.getPrice());

        item.setPrice(450.0);
        item.setSize("XL");

        assertEquals(450.0, item.getPrice());
        assertEquals("XL", item.getSize());
    }

    @Test
    void setIllegalExceptionsTest(){
        Clothes item = new Clothes("Джинси", "S", 900.25, "Синій");

        assertThrows(IllegalArgumentException.class,()->{item.setName("");});
        assertThrows(IllegalArgumentException.class,()->{item.setColor("");});
        assertThrows(IllegalArgumentException.class,()->{item.setSize("");});
        assertThrows(IllegalArgumentException.class,()->{item.setSize("B");});
        assertThrows(IllegalArgumentException.class,()->{item.setPrice(-1);});

        assertThrows(IllegalArgumentException.class,()->{new Clothes("","M",456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх","F",456.45,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх","M",-546,"Рожевий");});
        assertThrows(IllegalArgumentException.class,()->{new Clothes("Капелюх","M",456.45,"");});
    }
}
