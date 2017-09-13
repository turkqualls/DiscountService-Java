package com.pillartechnology.discountservice.service;

import java.util.ArrayList;
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

    public void applyDiscount(Discount discount) {

    }
}
