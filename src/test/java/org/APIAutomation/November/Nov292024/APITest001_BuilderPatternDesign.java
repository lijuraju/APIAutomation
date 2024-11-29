package org.APIAutomation.November.Nov292024;

public class APITest001_BuilderPatternDesign {

    public APITest001_BuilderPatternDesign test1(){
        System.out.println("Step 1 is started");
        System.out.println("Step 1 is completed");
        return this;
    }

    public APITest001_BuilderPatternDesign test2(){
        System.out.println("Step 2 is started");
        System.out.println("Step 2 is completed");
        return this;
    }

    public APITest001_BuilderPatternDesign test3(String name){
        System.out.println("Step 3 is started");
        System.out.println("Step 3 is completed" + name);
        return this;
    }

    public static void main(String[] args) {
        APITest001_BuilderPatternDesign obj = new APITest001_BuilderPatternDesign();
        obj.test1().test2().test3("Liju");
    }
}
