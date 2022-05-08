package com.example.L08springbootmvcannotations.controllers;

import collections.KeywordAnalyzerInterface;
import com.example.L08springbootmvcannotations.models.Product;
import com.example.L08springbootmvcannotations.services.AnalyticService;
import com.example.L08springbootmvcannotations.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private static Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired(required = false)
//    @Qualifier("basicKeywordAnalyzer")
    private KeywordAnalyzerInterface keywordAnalyzer;

    @Autowired
    private ProductService productService;

    @Autowired(required = false)
    private AnalyticService analyticService;


    @GetMapping
    public List<Product> getProduct(){
        logger.info("Get Request for product");
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

    @GetMapping("/getAllSearches")
    public List<String> getAllSearch(){
        return keywordAnalyzer.getAllKeywords();
    }


}
