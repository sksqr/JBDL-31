package com.example.L10springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private BranchRepo branchRepo;


//    public Employee createEmployee(EmployeeRequestDTO employeeDTO){
//        Employee employee = new Employee();
//        employee.setName(employeeDTO.getName());
//       employeeRepo.save(employee);
//       return employee;
//    }


    @Transactional(rollbackOn = {EmployeeAppException.class})
    public Employee createEmployee(Employee employee) throws EmployeeAppException {
        Integer value =10;
        Branch branch = branchRepo.findById(1).get();
        employee.setBranch(branch);
        addressRepo.save(employee.getAddress());
        employeeRepo.save(employee);
        System.out.println(value.equals(10));
        if(value.equals(10)){
            throw new EmployeeAppException();
        }
        return employee;
    }

}
