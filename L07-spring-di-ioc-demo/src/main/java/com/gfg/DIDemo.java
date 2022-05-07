package com.gfg;



public class DIDemo {

    public static void main(String[] args) {

        Car car1 = new Car();
        car1.setName("Tata Tiago");
        car1.setEngine(new Engine("New-Revetron",1200));
        car1.run();
        System.out.println(car1);

//        Car car2 = new Car();
//        car2.setName("Tata Nexon");
//        car2.setEngine(new Engine("New-Revetron",1500));
//        car2.run();
//        System.out.println(car2);


        Engine engine = new Engine("New-Revetron",1500);
//        //dependency injection for loose coupling
        Car car2 = new Car("Nexon", engine);
        car2.run();

        Car car3 = new Car("Hexon", new Engine("MegaPower",2000));
        car3.run();



    }
}
