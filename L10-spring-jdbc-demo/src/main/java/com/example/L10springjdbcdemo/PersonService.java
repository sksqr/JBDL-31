package com.example.L10springjdbcdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;


    public Person addPerson(Person person){
        return personDao.createPerson(person);
    }


    public Person getPersonById(Integer id){
        return personDao.getPersonById(id);
    }

    public Person updatePerson(Integer id, Person person){
        return personDao.updatePerson(id,person);
    }

}
