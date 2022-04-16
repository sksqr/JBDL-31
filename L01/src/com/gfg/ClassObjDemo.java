package com.gfg;

import com.shashi.Product;

public class ClassObjDemo {

    public static void main(String[] args) {
        Person person = new Person("rahul");
        person.age=7;
        person.walk();
        System.out.println("No. of Persons: "+Person.numberOfPerson);
        Person person2 = new Person("ram",25);
        person2.walk();
        System.out.println("No. of Persons: "+Person.numberOfPerson);

    }
}
