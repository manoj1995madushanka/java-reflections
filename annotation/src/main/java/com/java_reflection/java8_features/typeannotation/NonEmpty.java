package com.java_reflection.java8_features.typeannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
public @interface NonEmpty {
}
