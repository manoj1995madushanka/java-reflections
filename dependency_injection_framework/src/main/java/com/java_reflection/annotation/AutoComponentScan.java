package com.java_reflection.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AutoComponentScans.class)
public @interface AutoComponentScan {
    public String value();

}
