package com.java_reflection.reflectiondemo;

public class Cat {
    private String name;
    public int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    private void setName(String name){
        this.name = name;
    }

    private void privateFeed(String food){
        System.out.println("cat ate " + food);
    }

}
