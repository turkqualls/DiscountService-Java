package com.pillartechnology.discountservice;

public interface Discount {
    DiscountType getDiscountType();
    Double getDiscountAmount();
}
