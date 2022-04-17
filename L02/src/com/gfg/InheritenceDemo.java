package com.gfg;

import com.demo.PageSelection2Sort;
import com.demo.PageSelectionSort;

import java.util.ArrayList;
import java.util.List;

public class InheritenceDemo {

    public static void main(String[] args) {

        PageMergeSorting basePageSorting = new PageMergeSorting();

        List<Product> productList = new ArrayList<>();

        basePageSorting.sort(productList);


        PageSelectionSort pageSelectionSort = new PageSelectionSort();
        BasePageSorting basePageSorting2 = pageSelectionSort;

        basePageSorting2.sort(productList);

        BasePageSorting basePageSorting3 = new BasePageSorting();
//        PageSelectionSort pageSelectionSort1 = basePageSorting;




    }
}
