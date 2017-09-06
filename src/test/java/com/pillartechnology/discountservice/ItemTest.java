package com.pillartechnology.discountservice;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ItemTest {
    Item item;
    
    @Before
    public void setUp() throws Exception {
        item = new Item("Item", ItemType.Electronic, 1.0d);
    }

    @Test
    public void shouldReturnItemTypeFromItem() throws Exception {
        assertEquals(ItemType.Electronic, item.getItemType());
    }

    @Test
    public void shouldReturnItemPriceBeforeDiscountIsApplied() throws Exception {
        assertEquals(1.0d, item.getItemPrice(), 0);
    }

    @Test
    public void validateItemIsDiscountableByItem() throws Exception {
        assertTrue(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, item)));
    }

    @Test
    public void validateItemIsDiscountableByItemType() throws Exception {
        assertTrue(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, ItemType.Electronic)));
    }

    @Test
    public void validateItemIsDiscountAbleByItemOnDate() throws Exception {
        assertTrue(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, item, LocalDate.now())));
    }

    @Test
    public void validateItemIsDiscountAbleByItemTypeOnDate() throws Exception {
        assertTrue(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, ItemType.Electronic, LocalDate.now())));
    }

    @Test
    public void validateItemIsNotDiscountableByItem() throws Exception {
        assertFalse(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, new Item("Item 2", ItemType.Clothing, 1d)
        )));
    }

    @Test
    public void validateItemIsNotDiscountableByItemType() throws Exception {
        assertFalse(item.validateDiscount(new SingleItemDiscount(DiscountType.Percentage, 0d, ItemType.Clothing)));
    }

    @Test
    public void applyDiscountPercentageToItem() throws Exception {
        item.applyDiscountToItem(new SingleItemDiscount(DiscountType.Percentage, .5d, ItemType.Electronic));

        assertEquals(.5d, item.getItemPrice(),0);
    }

    @Test
    public void applyDiscountDollarAmountToItem() throws Exception {
        item.applyDiscountToItem(new SingleItemDiscount(DiscountType.Dollar, .5d, ItemType.Electronic));
        assertEquals(.5d, item.getItemPrice(), 0);
    }

    @Test
    public void getPriceOfItemWhenDiscountDoesNotApply() throws Exception {
        item.applyDiscountToItem(new SingleItemDiscount(DiscountType.Percentage, 50.0d, ItemType.Book));

        assertEquals(1.0d, item.getItemPrice(),0);
    }
}