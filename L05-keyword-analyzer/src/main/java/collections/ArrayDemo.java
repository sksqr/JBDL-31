package collections;

import java.util.ArrayList;
import java.util.List;

public class ArrayDemo {

    public static void main(String[] args) {
//        int n=30;
//        String[] names = new String[n];
//
//        names[0]="Priyam";
//        names[1]="Akshat";
//        names[2]="Priya";
//
//        for(int i=0; i<3; i++){
//            System.out.println(names[i]);
//        }
        //List
        List<String> students = new ArrayList<>();
        students.add("Priyam");
        students.add("Akshat");
        students.add("Priya");

        for(int i=0; i<students.size(); i++){
            System.out.println(students.get(i));
        }

    }
}
