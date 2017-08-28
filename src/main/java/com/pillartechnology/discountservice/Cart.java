package com.pillartechnology.discountservice;

import java.time.LocalDate;
import java.util.Collections;

public class Cart {

    private Double amountBeforeDiscount = Double.MIN_VALUE;
    private Double amountAfterDiscount = Double.MIN_VALUE;
    private Integer itemsInCart = Integer.MIN_VALUE;
    private Items items;

    public Cart(double amountBeforeDiscount) {
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(double amountBeforeDiscount, int itemsInCart) {
        this.amountBeforeDiscount = amountBeforeDiscount;
        this.itemsInCart = itemsInCart;
    }

    public Cart(double amountBeforeDiscount, Items items) {
        this.items = items;
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(Items items) {
        this.items = items;
        this.itemsInCart = items.size();
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
        return discount instanceof AllCartDiscount && ((AllCartDiscount) discount).isValid(this.items, this.itemsInCart);
    }

    private void applyDiscountToItem(DiscountInterface discount) {
        for(Item item : items){
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

    private double getTotalPriceOfItems() {
        double total = 0.0d;
        for(Item item : items)
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
