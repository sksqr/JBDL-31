package com.example.L10springjdbcdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao {

    @Value("${namedQuery}")
    private String namedQuery;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static Logger logger = LoggerFactory.getLogger(PersonDao.class);

    public Person updatePerson(Integer id, Person person){
        return person;
    }


    public Person getPersonById(int id){
        // using jdbcTemplate
//        String query ="select * from person where id="+id;
//        List<Person> personList = jdbcTemplate.query(query,new PersonRowMapper());

//        String namedQuery = "select * from person where id= :id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        List<Person> personList = namedParameterJdbcTemplate.query(namedQuery,mapSqlParameterSource,new PersonRowMapper());
        return personList.get(0);
    }

    public Person createPerson(Person person){
        String insertQuery = "insert into person values(null, :name, :email, :phone, null)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name",person.getName());
        mapSqlParameterSource.addValue("email",person.getEmail());
        mapSqlParameterSource.addValue("phone",person.getPhone());
        namedParameterJdbcTemplate.update(insertQuery,mapSqlParameterSource);
        return person;
    }

}