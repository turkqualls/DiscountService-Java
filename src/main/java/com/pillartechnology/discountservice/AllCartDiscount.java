package com.pillartechnology.discountservice;

import java.time.LocalDate;
import java.util.Collections;

public class AllCartDiscount implements DiscountInterface {

    private DiscountType discountType;
    private Double discountAmount;
    private LocalDate discountDate;
    private Integer itemLimit;
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

    public boolean isValid(Items items, int itemsCount) {
        return isSpecificDayDiscount() || isAmountOfItemsInCartDiscount(itemsCount) ||  isAmountOfSpecificItemsInCartDiscount(items) || isAmountOfSpecificItemTypeInCartDiscount(items);
    }

    private boolean isSpecificDayDiscount() {
        return this.discountDate != null && this.discountDate.equals(LocalDate.now());
    }

    private boolean isAmountOfItemsInCartDiscount(Integer itemsInCart){
        return itemsInCart >= this.itemLimit;
    }

    private boolean isAmountOfSpecificItemsInCartDiscount(Items items) {
        return this.item != null && Collections.frequency(items, this.item) >= this.itemLimit;
    }

    private boolean isAmountOfSpecificItemTypeInCartDiscount(Items items) {
        return this.itemType != null && items.stream().filter(item -> this.itemType.equals(item.getItemType())).count() >= this.itemLimit;
    }
}
