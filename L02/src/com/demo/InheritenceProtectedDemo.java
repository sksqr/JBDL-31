package com.demo;

import com.gfg.PageMergeSorting;
import com.gfg.Product;

import java.util.ArrayList;
import java.util.List;

public class InheritenceProtectedDemo {

    public static void main(String[] args) {


        List<Product> productList = new ArrayList<>();
        PageSelection2Sort pageSelection2Sort = new PageSelection2Sort();
        pageSelection2Sort.sort(productList);


    }
}
