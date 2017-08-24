package com.pillartechnology.discountservice;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {

    private Cart cart;
    private DiscountInterface discount;
    private List<Item> itemList;

    @Test
    public void getDiscountAmountFromCart(){
        cart = new Cart(100.0d);

        cart.applyDiscount(.2d);
        assertEquals(80.0d , cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartAfterDollarAmountDiscount(){
        cart = new Cart(100.0d);

        cart.applyDiscount(15, DiscountType.Dollar);
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
        itemList = new ArrayList<>();
        Item item = new Item("Item");
        itemList.add(item);
        itemList.add(item);

        cart = new Cart(100.0d, itemList);
        discount = new AllCartDiscount(DiscountType.Percentage, .20d, 2, item);

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }
}