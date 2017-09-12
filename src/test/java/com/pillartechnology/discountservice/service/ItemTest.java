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
import static org.hamcrest.Matchers.*;

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
        assertThat(item.getItemPrice(), is(1.0d));
        assertThat(item.getItemPriceBeforeDiscount(), is(1.0d));
        assertThat(item.getItemPriceAfterDiscount(), is(Double.MIN_VALUE));
    }

    @Test
    public void applyDiscountPercentageToItem_ReturnDiscountedPrice() throws Exception {
        when(discount.getDiscountType()).thenReturn(DiscountType.Percentage);
        when(discount.getDiscountAmount()).thenReturn(.5d);

        item.applyDiscountToItem(discount);

        assertThat(item.getItemPrice(), is(.5d));
        verify(discount).getDiscountType();
        verify(discount).getDiscountAmount();
    }

    @Test
    public void applyDiscountDollarAmountToItemReturnDiscountPrice() throws Exception {
        when(discount.getDiscountType()).thenReturn(DiscountType.Dollar);
        when(discount.getDiscountAmount()).thenReturn(.5d);

        item.applyDiscountToItem(discount);

        assertThat(item.getItemPrice(), is(.5d));
        verify(discount).getDiscountType();
        verify(discount).getDiscountAmount();
    }
}