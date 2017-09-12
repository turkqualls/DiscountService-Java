package com.pillartechnology.discountservice.util;

import com.pillartechnology.discountservice.domain.DiscountType;

public class CalculateDiscountHelper {
    public static Double calculateDiscount(DiscountType discountType, Double initialValue, Double discountValue){
        if(discountType.equals(DiscountType.Dollar))
            return initialValue - discountValue;
        else
            return initialValue * (1 - discountValue);
    }
}
