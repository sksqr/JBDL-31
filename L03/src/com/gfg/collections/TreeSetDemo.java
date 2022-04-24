package com.gfg.collections;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {

        Set<Employee> employeeSet = new TreeSet<>((e1,e2)->e1.getExp()-e2.getExp());

        employeeSet.add(new Employee("Rahul",3));
        employeeSet.add(new Employee("Rani",1));
        employeeSet.add(new Employee("Priyam",10));
        employeeSet.add(new Employee("Rani",1));

        for(Employee employee: employeeSet){
            System.out.println(employee);
        }

    }
}
