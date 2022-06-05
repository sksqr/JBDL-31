package com.example.L15springsecuritydemo.repo;

import com.example.L15springsecuritydemo.dbmodel.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser,Long> {
    MyUser findByUsername(String username);
}
