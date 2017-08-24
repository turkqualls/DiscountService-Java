package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class Cart {

    private double amountBeforeDiscount;
    private double amountAfterDiscount;
    private int itemsInCart;

    public Cart(double amountBeforeDiscount) {
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(double amountBeforeDiscount, int items) {
        this.amountBeforeDiscount = amountBeforeDiscount;
        this.itemsInCart = items;
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
        if(!discount.getDiscountDate().equals(LocalDate.now()))
            return;

        this.applyDiscount(discount.getDiscountAmount(), discount.getDiscountType());
    }
}
