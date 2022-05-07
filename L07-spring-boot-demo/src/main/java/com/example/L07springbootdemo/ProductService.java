package com.example.L07springbootdemo;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    static int nextId=1;

    static List<Product> productList = new ArrayList<>();

    public void addProduct(Product product){
        product.setId(nextId);
        nextId++;
        productList.add(product);
    }

    public List<Product> getProducts(){
        return productList;
    }

    public List<Product> getProductsByName(String name){
        List<Product> result = new ArrayList<>();
        for(Product product:productList){
            if(name.equals(product.getName())){
                result.add(product);
            }
        }
        return result;
    }


    public Product getProductById(Integer id){

        for(Product product:productList){
            if(id.equals(product.getId())){
                return product;
            }
        }
        return null;
    }
}
