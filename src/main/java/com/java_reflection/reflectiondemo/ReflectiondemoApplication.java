package com.java_reflection.reflectiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class ReflectiondemoApplication {

    public static void main(String[] args) {
        invokeUsingReflection();
        exploreMethods();
    }

    private static void invokeUsingReflection() {
        try {
            // get class that matches package and class name
            Class<?> privateClass = Class.forName("com.java_reflection.reflectiondemo.PrivateClass");
            // get declared private constructor
            Constructor<?> constructor = privateClass.getDeclaredConstructor();
            // set visibility to true of that constructor
            constructor.setAccessible(true);
            // create instance of private class
            PrivateClass instance = (PrivateClass) constructor.newInstance();

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * lets looks some methods provided by reflections
     */
    private static void exploreMethods() {
        try {
            // first see two class object for same class name equal or not
            Class<?> class1 = Class.forName("java.lang.String");
            Class<?> class2 = Class.forName("java.lang.String");

            // checking memory location equality
            System.out.println("memory location equality -> " +(class1 == class2));
            // checking content equality
            System.out.println("content equality -> " + class1.equals(class2));

            // get superclass
            Class<?> superClass = class1.getSuperclass();
            System.out.println( "Superclasses : "+superClass.getName());

            // get interfaces
            System.out.println("Listing interfaces");
            Class<?>[] interfaces = class1.getInterfaces();
            for (Class<?> intf : interfaces){
                System.out.println(intf.getName());
            }

            // access constructors
            Constructor<?>[] constructors = class1.getConstructors();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
