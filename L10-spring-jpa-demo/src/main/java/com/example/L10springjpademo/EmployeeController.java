package com.example.L10springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


//    @PostMapping
//    private ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) throws URISyntaxException {
//        Employee employee =  employeeService.createEmployee(employeeDTO);
//        URI location = new URI("/employee/"+employee.getId());
//        return ResponseEntity.created(location).body(employee);
//    }


    @PostMapping
    private ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException, EmployeeAppException {
        employee =  employeeService.createEmployee(employee);
        URI location = new URI("/employee/"+employee.getId());
        return ResponseEntity.created(location).body(employee);
    }

}
