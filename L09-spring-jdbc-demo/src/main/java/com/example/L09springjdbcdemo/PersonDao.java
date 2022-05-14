package com.example.L09springjdbcdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PersonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(PersonDao.class);

    public Person updatePerson(Integer id, Person person){
        return person;
    }


    public Person getPersonById(int id){
        String query ="select * from person where id="+id;
        Person person =null;
        return person;
    }

    public Person createPerson(Person person){
        return person;
    }
}