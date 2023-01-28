package com.java_reflection.generalpurposeannotation;

import java.util.ArrayList;
import java.util.List;

public class GeneralPurposeAnnotation extends DummyClass {

    /**
     * if we add @Override annotation to this method we can not modify this method structure
     * if we change method structure this will give compile time exception
     */
    @Override
    public void method1() {
        System.out.println("method1 implementation");
    }

    @Deprecated(since = "9")
    private void intDeprecate(int a){
        System.out.println("this method is deprecated of a =2");
    }

    /**
     * this method will show @SuppressWarning annotation usage
     * we can use that for hide warnings kind of things but not use this for that purpose
     */
    public void suppressWarnings() {
        // below line will show warning because a is unused
        // we can hide that using @SuppressWarning
        @SuppressWarnings("unused")
        int a = 5;

        // this will give two issues because we did not define type and list not used
        @SuppressWarnings({"rawType", "unused"})
        List list = new ArrayList();

        @SuppressWarnings("all")
        List list2 = new ArrayList();

    }


}
