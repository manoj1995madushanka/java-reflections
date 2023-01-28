package com.java_reflection.customannotation;

import java.lang.annotation.*;

/**
 * custom annotation to determine
 * which entity is most used from all the present entities
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MostUsedCustomAnnotation {

    String value() default "Java";
}
