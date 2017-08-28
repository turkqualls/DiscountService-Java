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
        this.amountBeforeDiscount = amountBeforeDiscount;
    }

    public Cart(List<Item> itemList) {
        this.itemList = itemList;
        getTotalPriceOfItems();
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return amountAfterDiscount;
    }

    public void applyDiscount(double discountAmount) {
        this.applyDiscount(discountAmount, DiscountType.Percentage);
    }

    public void applyDiscount(double discountAmount, DiscountType discountType) {
        if(discountType.equals(DiscountType.Dollar))
            this.amountAfterDiscount = this.amountBeforeDiscount - discountAmount;
        else
            this.amountAfterDiscount = this.amountBeforeDiscount * (1 - discountAmount);
    }

    public void applyDiscount(DiscountInterface discount) {
        if(!isValidDiscount(discount))
            return;

        if (discount instanceof SingleItemDiscount)
            this.applyDiscountToItem(discount);
        else
            this.applyDiscount(discount.getDiscountAmount(), discount.getDiscountType());
    }

    private void applyDiscountToItem(DiscountInterface discount) {
        for(Item item : itemList){
            if (canItemBeDiscounted(discount, item))
                applyDiscountToItem(discount, item);
            this.amountAfterDiscount += item.getItemPrice();
        }
    }

    private void applyDiscountToItem(DiscountInterface discount, Item item) {
        if(discount.getDiscountType().equals(DiscountType.Dollar))
            item.setItemPriceAfterDiscount(item.getItemPriceBeforeDiscount() - discount.getDiscountAmount());
        else
            item.setItemPriceAfterDiscount(item.getItemPriceBeforeDiscount() * (1 - discount.getDiscountAmount()));
    }

    private boolean canItemBeDiscounted(DiscountInterface discount, Item item) {
        return (doesDiscountApplyForItem(discount, item) ||
                doesDiscountApplyForItemType(discount, item)) && applyDiscountIfDateApply(discount);
    }

    private boolean doesDiscountApplyForItem(DiscountInterface discount, Item item){
        return discount.getItem() != null && item.equals(discount.getItem());
    }

    private boolean doesDiscountApplyForItemType(DiscountInterface discount, Item item){
        return discount.getItemType() != null && item.getItemType().equals(discount.getItemType());
    }

    private boolean applyDiscountIfDateApply(DiscountInterface discount){
        if(discount.getDiscountDate() != null)
            return  discount.getDiscountDate().equals(LocalDate.now());
        return true;
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

    private void getTotalPriceOfItems() {
        for(Item item : itemList)
            this.amountBeforeDiscount += item.getItemPriceBeforeDiscount();
    }
}
