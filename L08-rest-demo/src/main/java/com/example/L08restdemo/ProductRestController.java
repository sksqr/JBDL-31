package com.example.L08restdemo;

import com.example.L08restdemo.models.Product;
import com.example.L08restdemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
        product = productService.addProduct(product);
        URI uri = new URI("/product/"+product.getId());
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
        Product product = productService.getProductById(id);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
