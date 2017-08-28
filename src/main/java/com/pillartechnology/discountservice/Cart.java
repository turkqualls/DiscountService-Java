package com.pillartechnology.discountservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    private double amountBeforeDiscount = Double.MIN_VALUE;
    private double amountAfterDiscount = Double.MIN_VALUE;
    private int itemsInCart = Integer.MIN_VALUE;
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
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(List<Item> itemList) {
        this.itemList = itemList;
        this.amountBeforeDiscount = getTotalPriceOfItems();
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return amountAfterDiscount;
    }

    public void applyDiscount(double discountAmount) {
        this.amountAfterDiscount = calculateDiscount(DiscountType.Percentage, this.amountBeforeDiscount, discountAmount);
    }

    public void applyDiscount(double discountAmount, DiscountType discountType) {
        this.amountAfterDiscount = calculateDiscount(discountType, this.amountBeforeDiscount, discountAmount);
    }

    public void applyDiscount(DiscountInterface discount) {
        if(isDiscountForCart(discount))
            this.amountAfterDiscount = calculateDiscount(discount.getDiscountType(), this.amountBeforeDiscount, discount.getDiscountAmount());
        else
            this.applyDiscountToItem(discount);
    }

    private boolean isDiscountForCart(DiscountInterface discount){
        return discount instanceof AllCartDiscount && isValidDiscount(discount);
    }

    private void applyDiscountToItem(DiscountInterface discount) {
        for(Item item : itemList){
            if (canItemBeDiscounted(discount, item))
                applyDiscountToItem(discount, item);
            this.amountAfterDiscount += item.getItemPrice();
        }
    }

    private void applyDiscountToItem(DiscountInterface discount, Item item) {
        item.setItemPriceAfterDiscount(calculateDiscount(discount.getDiscountType(), item.getItemPriceBeforeDiscount
                (), discount.getDiscountAmount()));
    }

    private boolean canItemBeDiscounted(DiscountInterface discount, Item item) {
        return (doesDiscountApplyForItem(discount, item) ||
                doesDiscountApplyForItemType(discount, item)) && applyDiscountToItemIfDateApply(discount);
    }

    private boolean doesDiscountApplyForItem(DiscountInterface discount, Item item){
        return discount.getItem() != null && item.equals(discount.getItem());
    }

    private boolean doesDiscountApplyForItemType(DiscountInterface discount, Item item){
        return discount.getItemType() != null && item.getItemType().equals(discount.getItemType());
    }

    private boolean applyDiscountToItemIfDateApply(DiscountInterface discount){
            return  discount.getDiscountDate() == null || discount.getDiscountDate().equals(LocalDate.now());
    }

    private boolean isValidDiscount(DiscountInterface discount){
        return isSpecificDayDiscount(discount) || isAmountOfItemsInCartDiscount(discount) ||
                isAmountOfSpecificItemsInCartDiscount(discount) || isAmountOfSpecificItemTypeInCartDiscount(discount);
    }

    private boolean isSpecificDayDiscount(DiscountInterface discount) {
        return discount.getDiscountDate() != null && discount.getDiscountDate().equals(LocalDate.now());
    }

    private boolean isAmountOfItemsInCartDiscount(DiscountInterface discount){
        return this.itemsInCart >= discount.getDiscountItemLimit();
    }

    private boolean isAmountOfSpecificItemsInCartDiscount(DiscountInterface discount) {
        return discount.getItem() != null && Collections.frequency(this.itemList, discount.getItem()) >= discount
                .getDiscountItemLimit();
    }

    private boolean isAmountOfSpecificItemTypeInCartDiscount(DiscountInterface discount) {
        return discount.getItemType() != null && itemList.stream().filter(item -> discount.getItemType().equals(item.getItemType())).count() >= discount.getDiscountItemLimit();
}

    private double getTotalPriceOfItems() {
        double total = 0.0d;
        for(Item item : itemList)
            total += item.getItemPriceBeforeDiscount();
        return total;
    }

    private double calculateDiscount(DiscountType discountType, double initialValue, double discountValue){
        if(discountType.equals(DiscountType.Dollar))
            return initialValue - discountValue;
        else
            return initialValue * (1 - discountValue);
    }
}
