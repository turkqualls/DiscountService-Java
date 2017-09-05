package com.pillartechnology.discountservice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    Item item = new Item("Item", ItemType.Electronic, 1.0d);
    
    @Before
    public void setUp() throws Exception {
        
    }

    @Test
    public void applyDiscountToItem() throws Exception {
        item.applyDiscountToItem(new SingleItemDiscount(DiscountType.Percentage, .5d, ItemType.Electronic));

        assertEquals(.5d, item.getItemPrice(),0);
    }

    @Test
    public void getPriceOfItemWhenDiscountDoesNotApply() throws Exception {
        item.applyDiscountToItem(new SingleItemDiscount(DiscountType.Percentage, 50.0d, ItemType.Book));

        assertEquals(1.0d, item.getItemPrice(),0);
    }
}