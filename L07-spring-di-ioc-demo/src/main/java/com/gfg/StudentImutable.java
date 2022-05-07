package com.gfg;

import java.util.Date;

public class StudentImutable {
    private Integer rollNo;
    private String name;
    private Date dob;

    public StudentImutable() {
    }

    public StudentImutable(Integer rollNo, String name, Date dob) {
        this.rollNo = rollNo;
        this.name = name;
        this.dob = new Date(dob.getTime());
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return new Date(dob.getTime());
    }


    @Override
    public String toString() {
        return "StudentImutable{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
