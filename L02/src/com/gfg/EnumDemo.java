package com.gfg;

public class EnumDemo {

    public static void main(String[] args) {
        /*
        MON,
        mon,
        Monday
         */

        WeekDay weekDay = WeekDay.SUNDAY;

        System.out.println(weekDay.getValue());
        System.out.println(weekDay);
    }

}
