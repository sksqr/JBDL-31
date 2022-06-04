package com.example.L14resttemplatedemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/blog/{id}")
//    public ResponseEntity<String> getBlog(@PathVariable Long id){
//        String url="http://localhost:8080/blog/"+id;
//        String response = restTemplate.getForObject(url,String.class);
//        return ResponseEntity.ok(response);
//    }


    @GetMapping("/blog/{id}")
    public ResponseEntity<BlogResponseBody> getBlog(@PathVariable Long id){
        String url="http://localhost:8080/blog/"+id;
        BlogResponseBody response = restTemplate.getForObject(url,BlogResponseBody.class);
        return ResponseEntity.ok(response);
    }
}
