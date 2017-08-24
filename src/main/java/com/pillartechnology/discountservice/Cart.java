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

    private void setAmountAfterDiscount(double amountAfterDiscount) {
        this.amountAfterDiscount = amountAfterDiscount;
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

        if(discount.getDiscountDate() != null)
            return discount.getDiscountDate().equals(LocalDate.now());

        if(discount.getItem() != null)
            return Collections.frequency(this.itemList, discount.getItem()) >= discount.getDiscountItemLimit();

        return this.itemsInCart >= discount.getDiscountItemLimit();
    }
}
