package com.pillartechnology.discountservice;

public interface Discount {
    DiscountType getDiscountType();
    Double getDiscountAmount();
    Item getItem();
    ItemType getItemType();
    boolean validate(Items items);

}
