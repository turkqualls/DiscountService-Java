package com.pillartechnology.discountservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private double amountBeforeDiscount;
    private double amountAfterDiscount;
    private int itemsInCart;
    private List<Item> itemList;

    public Cart(double amountBeforeDiscount) {
        this.amountBeforeDiscount = amountBeforeDiscount;
        this.itemList = new ArrayList<>();
    }

    public Cart(double amountBeforeDiscount, int items) {
        this.amountBeforeDiscount = amountBeforeDiscount;
        this.itemsInCart = items;
        this.itemList = new ArrayList<>();
    }

    public Cart(double amountBeforeDiscount, List<Item> itemList) {
        this.itemList = itemList;
        //this.itemsInCart = itemList.size();
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(List<Item> itemList) {
        this.itemList = itemList;
        getTotalPriceOfItems();
    }

    private void setAmountAfterDiscount(double amountAfterDiscount) {
        this.amountAfterDiscount = amountAfterDiscount;
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return amountAfterDiscount;
    }

    public void applyDiscount(double discountAmount) {
        this.applyDiscount(discountAmount, DiscountType.Percentage);
    }

    public void applyDiscount(double discountAmount, DiscountType discountType) {
        if(discountType == DiscountType.Dollar)
            this.setAmountAfterDiscount(this.amountBeforeDiscount - discountAmount);
        else
            this.setAmountAfterDiscount(this.amountBeforeDiscount * (1 - discountAmount));
    }

    public void applyDiscount(DiscountInterface discount) {
        if(!isValidDiscount(discount))
            return;

        this.applyDiscount(discount.getDiscountAmount(), discount.getDiscountType());
    }

    private boolean isValidDiscount(DiscountInterface discount){
        return isSpecificDayDiscount(discount) || isAmountOfItemsInCartDiscount(discount) ||
                isAmountOfSpecificItemsInCartDiscount(discount) || isAmountOfSpecificItemTypeInCartDiscount(discount);
    }

    private boolean isSpecificDayDiscount(DiscountInterface discount) {
        if(discount.getDiscountDate() != null)
            return discount.getDiscountDate().equals(LocalDate.now());
        return false;
    }

    private boolean isAmountOfItemsInCartDiscount(DiscountInterface discount){
        return this.itemsInCart >= discount.getDiscountItemLimit();
    }

    private boolean isAmountOfSpecificItemsInCartDiscount(DiscountInterface discount) {
        if(discount.getItem() != null)
            return Collections.frequency(this.itemList, discount.getItem()) >= discount.getDiscountItemLimit();
        return false;
    }

    private boolean isAmountOfSpecificItemTypeInCartDiscount(DiscountInterface discount) {
        if(discount.getItemType() != null)
            return itemList.stream().filter(i -> discount.getItemType().equals(i.getItemType())).count() >= discount
                    .getDiscountItemLimit();
        return false;
    }

    private void getTotalPriceOfItems() {
        for(Item item : itemList)
            this.amountBeforeDiscount += item.getItemPrice();
    }
}
