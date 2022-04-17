package com.gfg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompositionDemo {

    public static void main(String[] args) {
        BasePageSorting merge = new PageMergeSorting();
        BasePageSorting insertion = new PageInsertionSorting();

        PageDynamicSorting pageDynamicSorting = new PageDynamicSorting(merge,insertion);

        List<Product> productList = new LinkedList<>();

        pageDynamicSorting.sort(productList);
    }
}
