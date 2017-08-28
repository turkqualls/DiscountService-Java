package com.pillartechnology.discountservice;

import java.time.LocalDate;

public class SingleItemDiscount implements Discount {

    private DiscountType discountType;
    private Double discountAmount;
    private LocalDate discountDate;
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
    public Double getDiscountAmount() {
        return this.discountAmount;
    }

    public boolean isValid(Item item) {
        return (isItemDiscountableBaseOnItem(item) || isItemDiscountableBaseOnItemType(item)) && doesDateApply();
    }

    private boolean isItemDiscountableBaseOnItem(Item item){
        return this.item != null && item.equals(this.item);
    }

    private boolean isItemDiscountableBaseOnItemType(Item item){
        return this.itemType != null && item.getItemType().equals(this.itemType);
    }

    private boolean doesDateApply(){
        return  this.discountDate == null || this.discountDate.equals(LocalDate.now());
    }
}
