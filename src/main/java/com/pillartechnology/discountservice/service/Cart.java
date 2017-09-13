package com.pillartechnology.discountservice.service;

import com.sun.istack.internal.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public int getNumberOfItemsInCart() throws Exception {
        return items.size();
    }

    public void addItem(Item item) throws Exception {
        items.add(item);
    }

    public void addItems(List<Item> itemsToAdd) throws Exception {
        items.addAll(itemsToAdd);
    }

    public double getTotalInCart() throws Exception {
        return items.stream().mapToDouble(Item::getItemPrice).sum();
    }

    public boolean isDiscountValid(Discount discount) throws Exception {
        return isDiscountableByAmountOfItem(discount) || isDiscountableByItemLimit(discount) || isDiscountableByDate
                (discount);
    }

    @NotNull
    private boolean isDiscountableByItemLimit(Discount discount) {
        return discount.getDiscountItemLimit() > 0 && items.size() >= discount.getDiscountItemLimit();
    }

    private boolean isDiscountableByDate(Discount discount) {
        return LocalDate.now().equals(discount.getDiscountDate());
    }

    private boolean isDiscountableByAmountOfItem(Discount discount) {
        System.out.println(discount.getItem());
        return Collections.frequency(items, discount.getItem()) >= discount.getDiscountItemLimit();
    }
}
