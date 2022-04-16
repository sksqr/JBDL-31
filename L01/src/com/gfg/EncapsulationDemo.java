package com.gfg;

public class EncapsulationDemo {

    public static void main(String[] args) {
        Adult adult1 = new Adult();
        //adult1.age=2;
        adult1.setAge(2);
        ID id = new ID();
        id.name="Rahul";

        adult1.setId(id);

        System.out.println(adult1);

        id.name="hacker";


        System.out.println(adult1);

        GFGCourses gfgCourses = new GFGCourses();
//        gfgCourses.courses.add("C++");
//        gfgCourses.courses.add("java");
//        gfgCourses.courses.add("painting");



    }
}
