package com.java_reflection.java8_features.repeatableannotation;

import java.lang.annotation.*;

/**
 * we need to crate one more annotation to store array of Designation objects
 * */
@Repeatable(value = Designations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Designation {
    String value() default  "Employee";
}
