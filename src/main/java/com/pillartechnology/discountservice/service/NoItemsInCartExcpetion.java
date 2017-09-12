package com.pillartechnology.discountservice.service;

public class NoItemsInCartExcpetion extends IllegalArgumentException {
    public NoItemsInCartExcpetion() {
        super("No Items in the Cart");
    }
}
