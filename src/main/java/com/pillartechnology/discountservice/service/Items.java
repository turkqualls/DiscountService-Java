package com.pillartechnology.discountservice.service;

import java.util.ArrayList;

public class Items extends ArrayList<Item> {

    public Double getTotalPriceOfItemsBeforeDiscount(){
        Double total = 0.0d;
        for(Item item : this)
            total += item.getItemPriceBeforeDiscount();
        return total;
    }

    public Double getTotalPriceOfItemsAfterDiscount(){
        Double total = 0.0d;
        for(Item item : this)
            total += item.getItemPrice();
        return total;
    }

    public void applyDiscountToItems(Discount discount){
        for(Item item : this){
            if(item.validateDiscount(discount)){
                item.applyDiscountToItem(discount);
            }
        }
    }
}