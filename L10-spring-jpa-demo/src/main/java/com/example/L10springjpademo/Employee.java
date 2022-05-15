package com.example.L10springjpademo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column
    private Double salary;

    @OneToOne
    private Address address;

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private Branch branch;

}
