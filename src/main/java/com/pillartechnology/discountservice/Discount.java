package com.pillartechnology.discountservice;

import com.pillartechnology.discountservice.domain.DiscountType;
import com.pillartechnology.discountservice.domain.ItemType;

import java.time.LocalDate;

public interface Discount {
    DiscountType getDiscountType();
    Double getDiscountAmount();
    Item getItem();
    ItemType getItemType();
    LocalDate getDiscountDate();

}
