package com.demo;

import com.gfg.BasePageSorting;
import com.gfg.Product;

import java.util.List;

public class PageSelectionSort extends BasePageSorting {

    public void sort(List<Product> productList){
        postProcessing();
        System.out.println("using PageSelectionSort");

    }
//
//    protected void postProcessing(){
//        System.out.println("postProcessing in PageSelectionSort");
//
//    }



}
