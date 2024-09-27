package com.deloitte.demo.service;

import com.deloitte.demo.model.Department;
import com.deloitte.demo.repository.DepartmentRepository;

import java.util.List;

public class DepartmentService {
    private DepartmentRepository departmentRepository = new DepartmentRepository();

    public Department addDepartment(Department department) {
        departmentRepository.save(department);
        return department; // Return the added department
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id);
    }

    public Department updateDepartment(int id, Department updatedDepartment) {
        return departmentRepository.updateDepartment(id, updatedDepartment);
    }

    public boolean deleteDepartment(int id) {
        return departmentRepository.deleteDepartment(id);
    }

    // Additional business logic methods can be added here
}
