package com.example.L10springjdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/person")
public class PersonRestController {

    @Autowired
    private PersonService productService;

    @PostMapping
    public ResponseEntity<Person> createProduct(@RequestBody Person person) throws URISyntaxException {
        person = productService.addPerson(person);
        URI uri = new URI("/product/"+person.getId());
        return ResponseEntity.created(uri).body(person);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Person> getProduct(@PathVariable("id") Integer id){
        Person product = productService.getPersonById(id);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{id}")
    private ResponseEntity<Person> getProduct(@PathVariable("id") Integer id,@RequestBody Person person)
    {
        Person product = productService.updatePerson(id,person);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
