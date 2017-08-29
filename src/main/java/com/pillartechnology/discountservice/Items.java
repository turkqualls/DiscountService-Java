package com.pillartechnology.discountservice;

import java.util.ArrayList;

class Items extends ArrayList<Item> {

    Double getTotalPriceOfItemsBeforeDiscount(){
        Double total = 0.0d;
        for(Item item : this)
            total += item.getItemPriceBeforeDiscount();
        return total;
    }

    Double getTotalPriceOfItemsAfterDiscount(){
        Double total = 0.0d;
        for(Item item : this)
            total += item.getItemPrice();
        return total;
    }

    void applyDiscountToItems(Discount discount){
        if(discount.validate(this)){
            for(Item item : this){
                if(item.validateDiscount(discount)){
                    item.applyDiscountToItem(discount);
                }
            }
        }
    }
}