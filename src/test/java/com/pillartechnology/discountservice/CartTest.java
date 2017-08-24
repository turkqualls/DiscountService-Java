package com.pillartechnology.discountservice;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void getDiscountAmountFromCart(){
        Cart cart = new Cart(100.0d);

        cart.applyDiscount(.2d);
        assertEquals(80.0d , cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartAfterDollarAmountDiscount(){
        Cart cart = new Cart(100.0d);

        cart.applyDiscount(15, DiscountType.Dollar);
        assertEquals(85.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForTheSpecialDay(){
        Cart cart = new Cart(100.0d);
        double discountAmount = .20d;
        DiscountInterface discount = new AllCartDiscount(DiscountType.Percentage, discountAmount, LocalDate.now());

        cart.applyDiscount(discount);
        assertEquals(80.0d, cart.getAmountAfterDiscount(), 2);
    }

    @Test
    public void getDiscountedPriceFromCartForCertainAmountOfItems(){
        Cart cart = new Cart(100.0d, 5);
        double discountAmount = .20d;
        DiscountInterface discount = new AllCartDiscount(DiscountType.Percentage, discountAmount, 5);
    }
}