package com.example.L07springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> getProduct(){
        return productService.getProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
         productService.addProduct(product);
    }

    @GetMapping("/byname")
    public List<Product> getProductByName(@RequestParam(value = "keyword") String keyword){
        return productService.getProductsByName(keyword);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Integer id){
        return productService.getProductById(id);
    }


//    @GetMapping("/{id}")
//    public List<Product> getProductById(@PathVariable(value = "id",required = false) Integer id){
//        if(id != null){
//            List<Product> list = new ArrayList<>();
//            list.add(productService.getProductById(id));
//            return list;
//        }
//        return productService.getProducts();
//    }

    @GetMapping("/menu")
    public String getMenu(){
        return "menu.html";
    }


}
