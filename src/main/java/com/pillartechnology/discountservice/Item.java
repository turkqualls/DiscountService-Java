package com.pillartechnology.discountservice;

public class Item {
    private String name;
    private ItemType itemType;
    private double itemPriceBeforeDiscount;
    private double itemPriceAfterDiscount;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public Item(String name, ItemType itemType, double itemPrice) {
        this.name = name;
        this.itemType = itemType;
        this.itemPriceBeforeDiscount = itemPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemPriceAfterDiscount(double itemPriceAfterDiscount) {
        this.itemPriceAfterDiscount = itemPriceAfterDiscount;
    }

    public double getItemPriceBeforeDiscount() {
        return itemPriceBeforeDiscount;
    }

    public double getItemPrice() {
        return this.itemPriceAfterDiscount > 0.0d ? this.itemPriceAfterDiscount : this.itemPriceBeforeDiscount;
    }
}
