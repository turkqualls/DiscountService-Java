package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class AllCartDiscount implements DiscountInterface {

    private DiscountType discountType;
    private double discountAmount;
    private LocalDate discountDate;
    private int itemLimit;
    private Item item;


    public AllCartDiscount(DiscountType discountType, double discountAmount, LocalDate discountDate) {
        this.setDiscountType(discountType);
        this.setDiscountAmount(discountAmount);
        this.setDiscountDate(discountDate);
    }

    public AllCartDiscount(DiscountType discountType, double discountAmount, int itemLimit) {
        this.setDiscountType(discountType);
        this.setDiscountAmount(discountAmount);
        this.setItemLimit(itemLimit);
    }

    public AllCartDiscount(DiscountType discountType, double discountAmount, int itemLimit, Item item) {
        this.setDiscountType(discountType);
        this.setDiscountAmount(discountAmount);
        this.setItemLimit(itemLimit);
        this.setItem(item);
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
    private void setItem(Item item) {
        this.item = item;
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
    public Item getItem() {
        return this.item;
    }

    @Override
    public int getDiscountItemLimit() { return this.itemLimit; }

    @Override
    public boolean isValidDiscount() {
        return false;
    }
}
