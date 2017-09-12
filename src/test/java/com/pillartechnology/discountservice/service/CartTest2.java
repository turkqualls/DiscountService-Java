package com.pillartechnology.discountservice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartTest2 {

    private Cart cart;

    @Mock
    private Item item;

    private static final double ERROR = 0.0000001;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void shouldAddItemToCart() throws Exception {
        cart.addItem(item);

        assertThat(cart.getNumberOfItemsInCart(), is(1));
    }

    @Test
    public void shouldAddMultipleItemsToCart() throws Exception {
        cart.addItems(new ArrayList<>(Arrays.asList(
                item,
                item,
                item
        )));

        assertThat(cart.getNumberOfItemsInCart(), is(3));
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

        assertThat(cart.getTotalInCart(), closeTo(30d, ERROR));
        verify(item, times(3)).getItemPrice();
    }
}