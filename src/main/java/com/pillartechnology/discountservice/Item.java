package com.pillartechnology.discountservice;

public class Item {
    private String name;
    private ItemType itemType;
    private double itemPrice;

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public Item(String name, ItemType itemType, double itemPrice) {
        this.name = name;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
    }

    public Item(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
