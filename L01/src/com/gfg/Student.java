package com.gfg;

public class Student extends Person{
    private int rollNum;

    private String course;

    public Student() {

    }

    @Override
    public void walk(){
        System.out.println("Student "+name+" is walking");
        super.walk();
    }


    public int getRollNum() {
        return rollNum;
    }

    public void setRollNum(int rollNum) {
        this.rollNum = rollNum;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }




}
