package com.gfg;

import java.util.List;

public class BasePageSorting {

    public void sort(List<Product> productList){
        postProcessing();
        System.out.println("using BasePageSorting");

    }

    protected void postProcessing(){
        System.out.println("postProcessing in BasePageSorting");

    }

}
