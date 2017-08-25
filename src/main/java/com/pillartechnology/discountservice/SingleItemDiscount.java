package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class SingleItemDiscount implements DiscountInterface {

    private DiscountType discountType;
    private double discountAmount;
    private LocalDate discountDate;
    private int itemLimit;
    private Item item;
    private ItemType itemType;

    public SingleItemDiscount(DiscountType discountType, double discountAmount, Item item) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.item = item;
    }

    public SingleItemDiscount(DiscountType discountType, double discountAmount, ItemType itemType) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.itemType = itemType;
    }

    public SingleItemDiscount(DiscountType discountType, double discountAmount, LocalDate discountDate, Item item) {
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.discountDate = discountDate;
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
    public int getDiscountItemLimit() {
        return this.itemLimit;
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
}
