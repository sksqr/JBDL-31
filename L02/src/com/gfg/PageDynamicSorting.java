package com.gfg;

import java.util.List;

public class PageDynamicSorting {

    private BasePageSorting megreSort;

    private BasePageSorting insertionSort;

    public PageDynamicSorting(BasePageSorting megreSort, BasePageSorting insertionSort) {
        this.megreSort = megreSort;
        this.insertionSort = insertionSort;
    }

    public void sort(List<Product> productList){
        if(productList.size()<=10){
            megreSort.sort(productList);
        }
        else{
            insertionSort.sort(productList);
        }
    }
}
