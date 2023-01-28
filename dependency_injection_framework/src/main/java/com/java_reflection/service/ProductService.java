package com.java_reflection.service;

import com.java_reflection.annotation.AutoComponent;
import com.java_reflection.annotation.AutoInject;
import com.java_reflection.pojo.Product;
import com.java_reflection.repository.ProductRepository;

import java.util.List;

/**
 * we need to identify which beans need to crate by framework
 * we identify those we add @AutoComponent annotation
 * */
@AutoComponent
public class ProductService {

    @AutoInject
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
