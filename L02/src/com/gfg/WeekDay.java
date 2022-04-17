package com.gfg;

public enum WeekDay {

    SUNDAY(1,"SUN"),MONDAY(2,"MON");

    private int num;
    private String value;

    public int getNum() {
        return num;
    }

    public String getValue() {
        return value;
    }

    private WeekDay(int num, String value){
        this.num = num;
        this.value =value;
    }

    @Override
    public String toString() {
        return "WeekDay{" +
                "num=" + num +
                ", value='" + value + '\'' +
                '}';
    }
}
