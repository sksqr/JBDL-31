package com.example.L08restdemo.services;


import com.example.L08restdemo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {



    static int nextId=1;

    static List<Product> productList = new ArrayList<>();

    @PostConstruct
    public void initProduct(){
        productList=new ArrayList<>();
        addProduct(new Product("mobile",10000.00));
        addProduct(new Product("laptop",50000.00));
        addProduct(new Product("smart band",3000.00));

    }


    public Product addProduct(Product product){
        product.setId(nextId);
        nextId++;
        productList.add(product);
        return product;
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

    public Product getProductsById(Integer id){


        for(Product product:productList){
            if(id.equals(product.getId())){
                return product;
            }
        }
        return null;
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
