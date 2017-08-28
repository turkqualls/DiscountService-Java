package com.pillartechnology.discountservice;

import java.util.ArrayList;

class Items extends ArrayList<Item> {

    private Double totalPriceOfItemsAfterDiscount;

    public Items() {
        this.totalPriceOfItemsAfterDiscount = 0.0d;
    }

    public Double getTotalPriceOfItemsAfterDiscount() {
        return totalPriceOfItemsAfterDiscount;
    }

    Double getTotalPriceOfItemsBeforeDiscount(){
        Double total = 0.0d;
        for(Item item : this)
            total += item.getItemPriceBeforeDiscount();
        return total;
    }

    void applyDiscountToItems(Discount discount){
        for(Item item : this){
            if (((SingleItemDiscount) discount).isValid(item))
                item.applyDiscountToItem(discount);
            this.totalPriceOfItemsAfterDiscount += item.getItemPrice();
        }
    }
}