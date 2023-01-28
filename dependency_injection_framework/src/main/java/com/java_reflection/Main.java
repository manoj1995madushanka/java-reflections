package com.java_reflection;

import com.java_reflection.config.AppConfig;
import com.java_reflection.context.ApplicationContext;
import com.java_reflection.pojo.Product;
import com.java_reflection.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        ProductService productService = context.getBean(ProductService.class);

        List<Product> items = new ArrayList<>();
        items.add(new Product("Watch", 40));
        items.add(new Product("Backpack", 20));
        items.add(new Product("Belt", 10));

        List<Product> finalPrice = productService.getFinalPrice(items);

        finalPrice.stream().forEach(p -> System.out.println(p.getName() + " @" + p.getDiscount() + " % discount : " + p.getPrice() + "$."));

    }
}