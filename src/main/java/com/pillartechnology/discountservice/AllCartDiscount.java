package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class AllCartDiscount implements DiscountInterface {

    private DiscountType discountType;
    private double discountAmount;
    private LocalDate discountDate;
    private int itemLimit;


    public AllCartDiscount(DiscountType discountType, double discountAmount, LocalDate discountDate) {
        this.setDiscountType(discountType);
        this.setDiscountAmount(discountAmount);
        this.setDiscountDate(discountDate);
    }

    public AllCartDiscount(DiscountType percentage, double discountAmount, int itemLimit) {
        this.setDiscountType(discountType);
        this.setDiscountAmount(discountAmount);
        this.setItemLimit(itemLimit);
    }

    private void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }
    private void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    private void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    private void setDiscountDate(LocalDate discountDate) {
        this.discountDate = discountDate;
    }

    @Override
    public DiscountType getDiscountType() {
        return this.discountType;
    }

    @Override
    public double getDiscountAmount() {
        return this.discountAmount;
    }

    @Override
    public LocalDate getDiscountDate() {
        return this.discountDate;
    }

    @Override
    public int getDiscountItemLimit() {
        return 0;
    }
}
