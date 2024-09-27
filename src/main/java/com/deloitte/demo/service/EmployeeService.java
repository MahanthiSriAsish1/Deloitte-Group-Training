package com.deloitte.demo.service;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepository.updateEmployee(id, updatedEmployee);
    }

    public boolean deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    // Additional business logic methods can be added here
}
