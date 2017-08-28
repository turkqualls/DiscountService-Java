package com.pillartechnology.discountservice;

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
        this.amountBeforeDiscount = items.getTotalPriceOfItemsBeforeDiscount();
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return this.amountAfterDiscount > Double.MIN_VALUE ? this.amountAfterDiscount : items.getTotalPriceOfItemsAfterDiscount();
    }

    public void applyDiscount(Double discountAmount) {
        this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(DiscountType.Percentage, this.amountBeforeDiscount, discountAmount);
    }

    public void applyDiscount(Double discountAmount, DiscountType discountType) {
        this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(discountType, this.amountBeforeDiscount, discountAmount);
    }

    public void applyDiscount(Discount discount) {
        if(isDiscountForCart(discount))
            this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(discount.getDiscountType(), this.amountBeforeDiscount, discount.getDiscountAmount());
        else
            items.applyDiscountToItems(discount);
    }

    private boolean isDiscountForCart(Discount discount){
        return discount instanceof AllCartDiscount && ((AllCartDiscount) discount).isValid(this.items, this.itemsInCart);
    }
}
