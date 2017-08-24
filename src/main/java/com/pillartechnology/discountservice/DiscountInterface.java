package com.pillartechnology.discountservice;

import java.time.LocalDate;

public interface DiscountInterface {
    DiscountType getDiscountType();
    double getDiscountAmount();
    int getDiscountItemLimit();
    LocalDate getDiscountDate();
    Item getItem();
}
