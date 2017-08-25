package com.pillartechnology.discountservice;

public class Item {
    private String name;
    private ItemType itemType;

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public Item(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
