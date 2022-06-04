package com.example.L14redisdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
//
//    @Value("${redis.url}")
//    private String redisUrl;
//
//    @Value("${redis.password}")
//    private String password;
//
//    @Value("${redis.port}")
//    private Integer port;
//
//
//    @Bean
//    public RedisConnectionFactory getRedisConnectionFactory(){
////        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
////        lettuceConnectionFactory.setHostName(redisUrl);
////        lettuceConnectionFactory.setPort(port);
////        return lettuceConnectionFactory;
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName(redisUrl);
//        jedisConnectionFactory.setPort(port);
//        return jedisConnectionFactory;
//    }

    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

}
