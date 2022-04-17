package com.gfg;

import com.AreaCalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class PolymorphismDemo {

    public static void main(String[] args) {

        //Runtime
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        BasePageSorting  basePageSorting = null;
        if(i==1){
            basePageSorting = new PageInsertionSorting();
        }
        else {
            basePageSorting = new PageMergeSorting();
        }
        basePageSorting.sort(new ArrayList<>());



        //compile time
        AreaCalculator areaCalculator = new AreaCalculator();
        areaCalculator.area(1);
        areaCalculator.area(1,2);



    }
}
