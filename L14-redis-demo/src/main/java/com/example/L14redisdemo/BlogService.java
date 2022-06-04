package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private String prefix = "blog_";

    private String nextIdKey = "nextId";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Long createBlog(Blog blog){
        Long id = redisTemplate.opsForValue().increment(nextIdKey);
        String key = prefix+id;
        redisTemplate.opsForValue().set(key,blog);
        return id;
    }


    public Blog getBlog(Long id){
        String key = prefix+id;
        Blog blog = (Blog) redisTemplate.opsForValue().get(key);
        return blog;
    }
}
