package com.pillartechnology.discountservice;

public class Item {
    private String name;
    private ItemType itemType;
    private Double itemPriceBeforeDiscount = Double.MIN_VALUE;
    private Double itemPriceAfterDiscount = Double.MIN_VALUE;

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public Item(String name, ItemType itemType, Double itemPrice) {
        this.name = name;
        this.itemType = itemType;
        this.itemPriceBeforeDiscount = itemPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Double getItemPriceBeforeDiscount() {
        return itemPriceBeforeDiscount;
    }

    public Double getItemPrice() {
        return this.itemPriceAfterDiscount > Double.MIN_VALUE ? this.itemPriceAfterDiscount : this.itemPriceBeforeDiscount;
    }

    public void applyDiscountToItem(Discount discount){
        this.itemPriceAfterDiscount = CalculateDiscountHelper.calculateDiscount(discount.getDiscountType(), this
                .itemPriceBeforeDiscount, discount.getDiscountAmount());
    }
}
