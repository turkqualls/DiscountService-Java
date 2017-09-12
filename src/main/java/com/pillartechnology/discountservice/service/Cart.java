package com.pillartechnology.discountservice.service;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public int getNumberOfItemsInCart() throws NoItemsInCartExcpetion {
        if (items.size() <= 0)
            throw new NoItemsInCartExcpetion();
        return items.size();
    }

    public void addItem(Item item) throws Exception {
        items.add(item);
    }

    public void addItems(List<Item> itemsToAdd) throws Exception {
        items.addAll(itemsToAdd);
    }

    public double getTotalInCart() throws Exception {
        double totalPrice = 0;
        for(Item item : items)
            totalPrice += item.getItemPrice();
        return totalPrice;
    }
}
