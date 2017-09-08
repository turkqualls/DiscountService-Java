package com.pillartechnology.discountservice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemsTest {

    private Items items;

    @Before
    public void setUp() throws Exception {
        items = new Items();
        items.add(new Item("Item", ItemType.Clothing, 10.0d));
        items.add(new Item("Item", ItemType.Electronic, 10.0d));
        items.add(new Item("Item", ItemType.Book, 10.0d));
    }

    @Test
    public void addItemsToItemsList() throws Exception {
        assertEquals(3, items.size());
    }

    @Test
    public void getTotalPriceOfItemsBeforeDiscountIsApplied() throws Exception {
        assertEquals(30.0d, items.getTotalPriceOfItemsBeforeDiscount(), 0);
    }

    @Test
    public void getTotalPriceOfItemsAfterDiscountIsApplied() throws Exception {
        items.applyDiscountToItems(new SingleItemDiscount(DiscountType.Percentage, .5d, ItemType.Clothing));

        assertEquals(25.00d, items.getTotalPriceOfItemsAfterDiscount(), 0);
    }
}