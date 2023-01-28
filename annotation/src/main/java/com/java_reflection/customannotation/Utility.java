package com.java_reflection.customannotation;

@MostUsedCustomAnnotation
public class Utility {

    void doStuff(){
        System.out.println("Doing something");
    }

    @MostUsedCustomAnnotation("Angular")
    void doStuff(String arg){
        System.out.println("Operation on : "+arg);
    }

    void doStuff(int arg){
        System.out.println("Operation on : "+arg);
    }
}

class SubUtility extends Utility{
    // this class also getting @MostUsedCustomAnnotation because it has @Inherited
}
