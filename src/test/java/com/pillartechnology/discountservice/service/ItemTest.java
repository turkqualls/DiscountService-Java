package com.pillartechnology.discountservice.service;

import com.pillartechnology.discountservice.domain.DiscountType;
import com.pillartechnology.discountservice.domain.ItemType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemTest {
    private Item item;

    @Mock
    private Discount discount;
    
    @Before
    public void setUp() throws Exception {
        item = new Item("Item", ItemType.Electronic, 1.0d);
    }

    @Test
    public void shouldReturnItemPriceBeforeDiscountIsApplied() throws Exception {
        assertEquals(1.0d, item.getItemPrice(), 0);
        assertEquals(1.0d, item.getItemPriceBeforeDiscount(), 0);
        assertEquals(Double.MIN_VALUE, item.getItemPriceAfterDiscount(), 0);
    }

    @Test
    public void applyDiscountPercentageToItem() throws Exception {
        when(discount.getDiscountType()).thenReturn(DiscountType.Percentage);
        when(discount.getDiscountAmount()).thenReturn(.5d);

        item.applyDiscountToItem(discount);

        assertEquals(.5d, item.getItemPrice(),0);
        verify(discount).getDiscountType();
        verify(discount).getDiscountAmount();
    }

    @Test
    public void applyDiscountDollarAmountToItem() throws Exception {
        when(discount.getDiscountType()).thenReturn(DiscountType.Dollar);
        when(discount.getDiscountAmount()).thenReturn(.5d);

        item.applyDiscountToItem(discount);

        assertEquals(.5d, item.getItemPrice(),0);
        verify(discount).getDiscountType();
        verify(discount).getDiscountAmount();
    }
}