package com.pillartechnology.discountservice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartTest2 {

    private Cart cart;

    @Mock
    private Item item;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void shouldAddItemToCart() throws Exception {
        cart.addItem(item);

        assertEquals(1, cart.getNumberOfItemsInCart());
    }

    @Test
    public void shouldAddMultipleItemsToCart() throws Exception {
        cart.addItems(new ArrayList<>(Arrays.asList(
                item,
                item,
                item
        )));

        assertEquals(3, cart.getNumberOfItemsInCart());
    }

    @Test(expected = NoItemsInCartExcpetion.class)
    public void shouldReturnNoItemsInCartException() throws Exception {
        assertEquals(0, cart.getNumberOfItemsInCart());
    }

    @Test
    public void shouldReturnTotalAmountOfAllItems() throws Exception {
        when(item.getItemPrice()).thenReturn(10d, 15d, 5d);

        cart.addItems(new ArrayList<>(Arrays.asList(
                item,
                item,
                item
        )));

        assertEquals(30d, cart.getTotalInCart(), 0);
        verify(item, times(3)).getItemPrice();
    }
}