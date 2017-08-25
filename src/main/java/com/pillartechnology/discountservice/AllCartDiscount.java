package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class AllCartDiscount implements DiscountInterface {

    private DiscountType discountType;
    private double discountAmount;
    private LocalDate discountDate;
    private int itemLimit;
    private Item item;
    private ItemType itemType;


    public AllCartDiscount(DiscountType discountType, double discountAmount, LocalDate discountDate) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.discountDate = discountDate;
    }

    public AllCartDiscount(DiscountType discountType, double discountAmount, int itemLimit) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.itemLimit = itemLimit;
    }

    public AllCartDiscount(DiscountType discountType, double discountAmount, int itemLimit, Item item) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.itemLimit = itemLimit;
        this.item = item;
    }

    public AllCartDiscount(DiscountType discountType, double discountAmount, int itemLimit, ItemType itemType) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.itemLimit = itemLimit;
        this.itemType = itemType;
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
    public ItemType getItemType() {
        return this.itemType;
    }

    @Override
    public int getDiscountItemLimit() {
        return this.itemLimit;
    }
}
