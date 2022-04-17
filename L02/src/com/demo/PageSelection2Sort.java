package com.demo;

import com.demo.PageSelectionSort;
import com.gfg.Product;

import java.util.List;

public class PageSelection2Sort extends PageSelectionSort {


    public void sort(List<Product> productList){
        postProcessing();
        System.out.println("using PageSelection2Sort");

    }
}
