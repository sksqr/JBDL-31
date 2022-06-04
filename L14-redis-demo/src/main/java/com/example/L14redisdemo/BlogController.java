package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private String visitorQueueKey = "Visitor_Queue";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private BlogService blogService;


    @PostMapping
    public ResponseEntity<Long> createBlog(@RequestBody Blog blog){
        Long id = blogService.createBlog(blog);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id){
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok(blog);
    }



    @PostMapping("/addVisitor")
    public ResponseEntity<Long> addVisitor(@RequestParam String name){
        Long size = redisTemplate.opsForList().leftPush(visitorQueueKey,name);
        return ResponseEntity.ok(size);
    }



}
