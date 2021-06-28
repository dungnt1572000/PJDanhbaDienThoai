package com.example.danhbadienthoai.event;

import com.example.danhbadienthoai.databaseproduct.Product;

public class AddProductEvent {
    public Product getProd() {
        return prod;
    }

    private Product prod;

    public AddProductEvent(Product sp) {
        this.prod = sp;
    }
}
