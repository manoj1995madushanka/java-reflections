package com.java_reflection.reflectiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

@SpringBootApplication
public class ReflectiondemoApplication {

    public static void main(String[] args) {
        //invokeUsingReflection();
        //exploreMethods();
        //fieldReflection();
        methodReflection();
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
            System.out.println("memory location equality -> " + (class1 == class2));
            // checking content equality
            System.out.println("content equality -> " + class1.equals(class2));

            // get superclass
            Class<?> superClass = class1.getSuperclass();
            System.out.println("Superclasses : " + superClass.getName());

            // get interfaces
            System.out.println("Listing interfaces");
            Class<?>[] interfaces = class1.getInterfaces();
            for (Class<?> intf : interfaces) {
                System.out.println(intf.getName());
            }

            // access constructors
            Constructor<?>[] constructors = class1.getConstructors();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * modify private fields using constructor
     */
    private static void fieldReflection() {
        Cat cat = new Cat("Tom", 2);
        // get class object
        Class<?> catClass = cat.getClass();
        // access only public fields of cat class
        Field[] publicFields = catClass.getFields();
        System.out.println("public fields => ");
        Stream.of(publicFields).forEach(System.out::println);

        // access public and private fields
        Field[] publicPrivateFields = catClass.getDeclaredFields();
        System.out.println("public and private fields => ");
        Stream.of(publicPrivateFields).forEach(System.out::println);

        try {
            System.out.println("cat name before modify : " + cat.getName());
            // modify private field value
            // here we need to use declaredField because we are accessing private field
            Field privateField = catClass.getDeclaredField("name");

            // before we modify private field we need to make that accessible
            privateField.setAccessible(true);

            privateField.set(cat, "Jerrry");
            System.out.println("cat name after modify : " + cat.getName());

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method level reflection
     */
    private static void methodReflection() {
        Cat cat = new Cat("Tom", 2);
        Class<?> catClass = cat.getClass();

        // access public methods of cat and it's super class
        Method[] methods = catClass.getMethods();
        System.out.println("public methods : ");
        Stream.of(methods).forEach(System.out::println);

        // access public and private methods of cat class
        Method[] publicPrivateMethods = catClass.getDeclaredMethods();
        System.out.println("public and private methods of cat class : ");
        Stream.of(publicPrivateMethods).forEach(System.out::println);

        try {
            // access private method and change value
            System.out.println("cate name before modify : " + cat.getName());
            Method setNameMethod = catClass.getDeclaredMethod("setName", String.class);
            setNameMethod.setAccessible(true);
            setNameMethod.invoke(cat, "Jerry");
            System.out.println("cate name after modify : " + cat.getName());

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
