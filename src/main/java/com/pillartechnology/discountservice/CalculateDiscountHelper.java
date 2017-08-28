package com.pillartechnology.discountservice;

public class CalculateDiscountHelper {
    static Double calculateDiscount(DiscountType discountType, Double initialValue, Double discountValue){
        if(discountType.equals(DiscountType.Dollar))
            return initialValue - discountValue;
        else
            return initialValue * (1 - discountValue);
    }
}
