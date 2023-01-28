package com.java_reflection;

import com.java_reflection.customannotation.typeannotation.Java8TypeAnnotation;
import com.java_reflection.customannotation.typeannotation.NonEmpty;
import com.java_reflection.customannotation.typeannotation.ReadOnly;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // this is calls type annotations
        Java8TypeAnnotation<String> box = new @NonEmpty @ReadOnly Java8TypeAnnotation<>(10,"Container");
    }
}