package com.java_reflection.customannotation.typeannotation;

public class Java8TypeAnnotation<@NonEmpty T> {
    @NonEmpty
    int size;
    T type;

    public Java8TypeAnnotation(int size, T type) {
        this.size = size;
        this.type = type;
    }

    class NestedClass extends Java8TypeAnnotation<T> {
        NestedClass(int size, @NonEmpty T type) {
            super(size, type);
        }
    }
}
