package com.app.registration.service;

import com.app.registration.exception.UserNotFoundException;
import com.app.registration.model.Employee;
import com.app.registration.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    //call the repo class here so we can use it on the service class of employees
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) {
        //generate employee uuid code
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    //find all employee
    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    //update employees
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    //find employee by id
    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
    }

    //delete employees
    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }

    public Employee fetchEmployeeByEmail(String email) {
       return employeeRepo.findByEmail(email);
    }

}
