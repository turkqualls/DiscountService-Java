package com.pillartechnology.discountservice;

import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CartTest {

    private Cart cart;
    private Discount discount;
    private Items items;
    private Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
    private Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
    private Item bookItem = new Item("Book", ItemType.Book, 10.0d);

    @Before
    public void setup(){
        items = new Items();

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);
    }

    @Test
    public void getPriceFromCartWhenItemsAreAdded(){
        cart = new Cart(items);
        assertEquals(30.0d, cart.getAmountBeforeDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedForAnItem() {
        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d, bookItem);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDollarDiscountIsAppliedForAnItem() {
        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Dollar, 5, bookItem);

        cart.applyDiscount(discount);
        assertEquals(25d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedToSpecificTypeOfItem() {
        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d, ItemType.Book);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedToSpecificItemForTheDay() {
        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d,  LocalDate.now(), bookItem);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedToSpecificItemTypeForTheDay() {
        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d,  LocalDate.now(), ItemType.Book);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }
}