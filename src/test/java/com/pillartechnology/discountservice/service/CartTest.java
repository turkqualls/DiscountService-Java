package com.pillartechnology.discountservice.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartTest {

    private Cart cart;
    private Discount discount;

    @Mock
    private Item item;

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

    @Test
    public void shouldReturnTotalAmountOfAllItems() throws Exception {
        cart.addItems(new ArrayList<>(Arrays.asList(
                item,
                item,
                item
        )));

        when(item.getItemPrice()).thenReturn(10d, 15d, 5d);

        assertThat(cart.getTotalInCart(), is(30d));
        verify(item, times(3)).getItemPrice();
    }

    @Test
    public void shouldValidateDiscountForAllCartForDate() throws Exception {
        cart.addItems(new ArrayList<>(Arrays.asList(
                item,
                item,
                item
        )));

        when(item.getItemPrice()).thenReturn(10d, 15d, 5d);

//        assertThat(cart.getTotalInCart(), closeTo(15d, 0d));
        assertEquals(15d, cart.getTotalInCart(), 0d);
        verify(item, times(3)).getItemPrice();
    }
}