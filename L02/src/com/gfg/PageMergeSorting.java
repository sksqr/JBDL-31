package com.gfg;

import java.util.List;

public class PageMergeSorting extends BasePageSorting{

    public void sort(List<Product> productList){
        System.out.println("using PageMergeSorting");

    }


    protected void postProcessing(){
        System.out.println("postProcessing in BasePageSorting");

    }


}
