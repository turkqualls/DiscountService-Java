package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class MultipleQuantityDiscount implements DiscountInterface {


    @Override
    public DiscountType getDiscountType() {
        return null;
    }

    @Override
    public double getDiscountAmount() {
        return 0;
    }

    @Override
    public int getDiscountItemLimit() {
        return 0;
    }

    @Override
    public LocalDate getDiscountDate() {
        return null;
    }

    @Override
    public Item getItem() {
        return null;
    }

    @Override
    public ItemType getItemType() {
        return null;
    }
}
