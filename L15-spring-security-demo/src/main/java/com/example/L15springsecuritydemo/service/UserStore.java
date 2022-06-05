package com.example.L15springsecuritydemo.service;

import com.example.L15springsecuritydemo.dbmodel.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserStore implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, MyUser> userMap = new HashMap<>();

    @PostConstruct
    public void init(){
        userMap.put("shashi",new MyUser("shashi",passwordEncoder.encode("123"),"user"));
        userMap.put("ravi",new MyUser("ravi",passwordEncoder.encode("123"),"admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userMap.get(username);
        if(userDetails==null){
            throw new UsernameNotFoundException("No user with username "+username);
        }
        return userDetails;
    }
}
