package com.jgeekmz.ManagementApp.services;

import java.util.List;
import java.util.Optional;

import com.jgeekmz.ManagementApp.models.Employee;
import com.jgeekmz.ManagementApp.models.User;
import com.jgeekmz.ManagementApp.repositories.EmployeeRepository;

import com.jgeekmz.ManagementApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private UserRepository userRepo;

    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepo) {
        this.userRepo = userRepo;
    }

        //Get All Employees
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    //Get Employee By Id
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    //Delete Employee
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    //Update Employee
    public void save( Employee employee) {
        employeeRepository.save(employee);
    }

    //Get Employee by username
    public Employee findByUsername(String un) {
        return employeeRepository.findByUsername(un);
    }

/*    public void assignUsername(int id) {
        Employee emp = employeeRepository.findById(id).orElse(null);
        User user = userRepo.findByFirstnameAndLastname(emp.getFirstname(), emp.getLastname());
        //if user is empty == null
        if (user == null) {
            // TODO
            emp.setUsername("No user found!");
            employeeRepository.save(emp);
        } else {
            emp.setUsername(user.getUsername());
            employeeRepository.save(emp);
        }
    }*/

    public List<Employee> findEmployeeByKeaword(String keywordSearch) {
        return employeeRepository.findEmployeeByKeyword(keywordSearch);
    }
}