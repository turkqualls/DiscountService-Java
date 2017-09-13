package com.pillartechnology.discountservice.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.cglib.core.Local;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
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
    public void shouldValidateDiscountBaseOnDate() throws Exception {
        discount = mock(Discount.class);

        when(discount.getDiscountDate()).thenReturn(LocalDate.now());

        assertTrue(cart.isDiscountValid(discount));
    }

    @Test
    public void shouldValidateDiscountBaseOnNumberOfItemsInCart() throws Exception {
        cart.addItem(item);
        discount = mock(Discount.class);

        when(discount.getDiscountItemLimit()).thenReturn(1);

        assertTrue(cart.isDiscountValid(discount));
    }

    @Test
    public void shouldValidateDiscountBaseOnItemInCart() throws Exception {
        cart.addItem(item);
        discount = mock(Discount.class);

        when(discount.getItem()).thenReturn(item);

        assertTrue(cart.isDiscountValid(discount));
    }
}