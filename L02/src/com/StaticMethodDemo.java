package com;

import com.gfg.AreaCalci;

public class StaticMethodDemo {

    public static void main(String[] args) {
        AreaCalculator areaCalculator = new AreaCalci();
        areaCalculator.staticMethod();


        AreaCalci areaCalci = new AreaCalci();
        areaCalci.staticMethod();

    }
}
