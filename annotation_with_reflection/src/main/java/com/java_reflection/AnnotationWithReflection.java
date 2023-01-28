package com.java_reflection;

import com.java_reflection.customannotation.MostUsedCustomAnnotation;
import com.java_reflection.customannotation.Utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationWithReflection {
    public static void main(String[] args) {

        try {
            Class<?> utilityClass = Class.forName("com.java_reflection.customannotation.Utility");
            Constructor<?> utilityConstructor = utilityClass.getConstructor();
            Utility utility = (Utility) utilityConstructor.newInstance();

            Method[] methods = utilityClass.getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(MostUsedCustomAnnotation.class)) {

                    method.setAccessible(true);
                    // get default value of annotation
                    MostUsedCustomAnnotation annotation = method.getAnnotation(MostUsedCustomAnnotation.class);
                    String defaultValue = annotation.value();
                    System.out.println("Annotation default value : " + defaultValue);

                    method.invoke(utility, "Scala");
                }
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}