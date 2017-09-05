package com.pillartechnology.discountservice;

public class Cart {

    private Double amountBeforeDiscount = Double.MIN_VALUE;
    private Double amountAfterDiscount = Double.MIN_VALUE;

    private Items items;

    public Cart(Items items) {
        this.items = items;
        this.amountBeforeDiscount = items.getTotalPriceOfItemsBeforeDiscount();
    }

    public double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    public double getAmountAfterDiscount() {
        return this.amountAfterDiscount > Double.MIN_VALUE ? this.amountAfterDiscount : items.getTotalPriceOfItemsAfterDiscount();
    }

    public void applyDiscount(Discount discount) {
        if(isDiscountForCart(discount))
            this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(discount.getDiscountType(), this.amountBeforeDiscount, discount.getDiscountAmount());
        else
            items.applyDiscountToItems(discount);
    }

    private boolean isDiscountForCart(Discount discount){
        return discount instanceof AllCartDiscount && discount.validate(this.items);
    }
}
