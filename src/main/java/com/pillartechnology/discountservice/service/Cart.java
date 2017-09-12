package com.pillartechnology.discountservice.service;

import com.pillartechnology.discountservice.util.CalculateDiscountHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public int getNumberOfItemsInCart() throws NoItemsInCartExcpetion {
        if (items.size() <= 0)
            throw new NoItemsInCartExcpetion();
        return items.size();
    }

    public void addItem(Item item) throws Exception {
        items.add(item);
    }

    public void addItems(List<Item> itemsToAdd) throws Exception {
        items.addAll(itemsToAdd);
    }

    public double getTotalInCart() throws Exception {
        double totalPrice = 0;
        for(Item item : items)
            totalPrice += item.getItemPrice();
        return totalPrice;
    }

    /*private Double amountBeforeDiscount = Double.MIN_VALUE;
    private Double amountAfterDiscount = Double.MIN_VALUE;
    private ArrayList<Item> Items;

    private Items items;

    public Cart(Items items) {
        this.items = items;
        this.amountBeforeDiscount = items.getTotalPriceOfItemsBeforeDiscount();
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return this.amountAfterDiscount > Double.MIN_VALUE ? this.amountAfterDiscount : items.getTotalPriceOfItemsAfterDiscount();
    }

    public void applyDiscount(Discount discount) {
        if(isDiscountForCart(discount))
            this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(discount.getDiscountType(), this.amountBeforeDiscount, discount.getDiscountAmount());
        else
            items.applyDiscountToItems(discount);
    }

    private boolean isDiscountForCart(Discount discount){
        return discount instanceof AllCartDiscount && isValidForAllCartDiscount((AllCartDiscount) discount);
    }

    private boolean isValidForAllCartDiscount(AllCartDiscount discount){
        return discount.validate(this.items);
    }*/
}
