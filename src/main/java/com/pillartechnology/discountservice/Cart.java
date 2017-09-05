package com.pillartechnology.discountservice;

class Cart {

    private Double amountBeforeDiscount = Double.MIN_VALUE;
    private Double amountAfterDiscount = Double.MIN_VALUE;

    private Items items;

    Cart(Items items) {
        this.items = items;
        this.amountBeforeDiscount = items.getTotalPriceOfItemsBeforeDiscount();
    }

    double getAmountBeforeDiscount() {
        return amountBeforeDiscount;
    }

    double getAmountAfterDiscount() {
        return this.amountAfterDiscount > Double.MIN_VALUE ? this.amountAfterDiscount : items.getTotalPriceOfItemsAfterDiscount();
    }

    void applyDiscount(Discount discount) {
        if(isDiscountForCart(discount))
            this.amountAfterDiscount = CalculateDiscountHelper.calculateDiscount(discount.getDiscountType(), this.amountBeforeDiscount, discount.getDiscountAmount());
        else
            items.applyDiscountToItems(discount);
    }

    private boolean isDiscountForCart(Discount discount){
        return discount instanceof AllCartDiscount && discount.validate(this.items);
    }
}
