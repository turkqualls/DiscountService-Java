package com.pillartechnology.discountservice;

public class MultipleQuantityDiscount implements Discount {


    @Override
    public DiscountType getDiscountType() {
        return null;
    }

    @Override
    public Double getDiscountAmount() {
        return Double.MIN_VALUE;
    }
}
