package com.pillartechnology.discountservice;

import org.junit.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CartTest {

    private Cart cart;
    private Discount discount;
    private Items items;

    @Before
    public void setup(){

    }

    @Test
    public void getDiscountAmountFromCart(){
        cart = new Cart(100.0d);

        cart.applyDiscount(.2d);
        assertEquals(80.0d , cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartAfterDollarAmountDiscount(){
        cart = new Cart(100.0d);

        cart.applyDiscount(15.0d, DiscountType.Dollar);
        assertEquals(85.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForTheSpecialDay(){
        cart = new Cart(100.0d);
        discount = new AllCartDiscount(DiscountType.Percentage, .20d, LocalDate.now());

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForCertainAmountOfItems(){
        cart = new Cart(100.0d, 5);
        discount = new AllCartDiscount(DiscountType.Percentage, .20d, 5);

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForCertainAMountOfSpecificItem(){
        items = new Items();
        Item item = new Item("Item");
        items.add(item);
        items.add(item);

        cart = new Cart(100.0d, items);
        discount = new AllCartDiscount(DiscountType.Percentage, .20d, 2, item);

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForCertainItemTypesInCart(){
        items = new Items();

        Item electronicItem = new Item("Item", ItemType.Electronic);
        Item electronic2Item = new Item("Item2", ItemType.Electronic);
        Item clothingItem = new Item("Item4", ItemType.Clothing);
        Item clothing2Item = new Item("Item3", ItemType.Clothing);
        Item clothing3Item = new Item("Item3", ItemType.Clothing);

        items.add(electronicItem);
        items.add(electronic2Item);
        items.add(clothingItem);
        items.add(clothing2Item);
        items.add(clothing3Item);

        cart = new Cart(100.0d, items);
        discount = new AllCartDiscount(DiscountType.Percentage, .20d, 3, ItemType.Clothing);

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceFromCartWhenItemsAreAdded(){
        items = new Items();
        Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
        Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
        Item bookItem = new Item("Book", ItemType.Book, 10.0d);

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);

        cart = new Cart(items);
        assertEquals(30.0d, cart.getAmountBeforeDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedForAnItem() {
        items = new Items();

        Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
        Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
        Item bookItem = new Item("Book", ItemType.Book, 10.0d);

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);

        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d, bookItem);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDollarDiscountIsAppliedForAnItem() {
        items = new Items();

        Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
        Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
        Item bookItem = new Item("Book", ItemType.Book, 10.0d);

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);

        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Dollar, 5, bookItem);

        cart.applyDiscount(discount);
        assertEquals(25d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedToSpecificTypeOfItem() {
        items = new Items();

        Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
        Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
        Item bookItem = new Item("Book", ItemType.Book, 10.0d);

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);

        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d, ItemType.Book);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getPriceOfCartWhenSpecialDiscountIsAppliedToSpecificOfItemForTheDay() {
        items = new Items();

        Item electronicItem = new Item("Electric", ItemType.Electronic, 10.0d);
        Item clothingItem = new Item("Clothes", ItemType.Clothing, 10.0d);
        Item bookItem = new Item("Book", ItemType.Book, 10.0d);

        items.add(electronicItem);
        items.add(clothingItem);
        items.add(bookItem);

        cart = new Cart(items);
        discount = new SingleItemDiscount(DiscountType.Percentage, .25d,  LocalDate.now(), bookItem);

        cart.applyDiscount(discount);
        assertEquals(27.5d, cart.getAmountAfterDiscount(), 2);
    }
}