package com.java_reflection.service;

import com.java_reflection.pojo.Product;
import com.java_reflection.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getFinalPrice(List<Product> items) {
        List<Product> list = productRepository.getPrice(items);

        for (Product product : list) {
            product.setPrice(product.getPrice() * (100 - product.getDiscount()) / 100);
            System.out.println("Price od " + product.getName() + " after " + product.getDiscount() + "% discount is " + product.getPrice());
        }
        return list;
    }
}
