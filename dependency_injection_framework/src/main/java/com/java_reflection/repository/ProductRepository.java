package com.java_reflection.repository;

import com.java_reflection.pojo.Product;

import java.util.List;

public class ProductRepository {

    public List<Product> getPrice(List<Product> items) {
        for (Product product : items) {
            double price = Math.round(30 * Math.random());
            System.out.println("Original price of " + product.getPrice() + " is " + price + " $.");
            product.setPrice(price);
        }
        return items;
    }
}
