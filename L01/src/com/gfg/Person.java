package com.gfg;

import java.util.Date;

public class Person {

    static int numberOfPerson=0;

    String name;



    int age;

    public Person(){
        numberOfPerson++;

    }

    public Person(String str){
        numberOfPerson++;
        name = str;
    }

    public Person(String str, int age){
        numberOfPerson++;
        name = str;
        this.age=age;
    }

    public void walk(){
        Date time = new Date();
        System.out.println(name+" is walking, time :"+time);
    }

    public static int getNumberOfPerson() {
        return numberOfPerson;
    }

    public static void setNumberOfPerson(int numberOfPerson) {
        Person.numberOfPerson = numberOfPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
