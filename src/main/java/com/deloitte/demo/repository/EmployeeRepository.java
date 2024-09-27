package com.deloitte.demo.repository;

import com.deloitte.demo.model.Employee;
import com.deloitte.demo.config.PersistenceConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager entityManager;

    public EmployeeRepository() {
        this.entityManager = PersistenceConfig.getEntityManagerFactory().createEntityManager();
    }

    // Add Employee (similar to your save method)
    public Employee addEmployee(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee != null ? Optional.of(employee) : Optional.empty();
    }

    // Update employee
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Employee existingEmployee = entityManager.find(Employee.class, id);
            if (existingEmployee != null) {
                transaction.begin();
                existingEmployee.setName(updatedEmployee.getName());
                existingEmployee.setSalary(updatedEmployee.getSalary());
                existingEmployee.setDepartmentId(updatedEmployee.getDepartmentId());
                entityManager.merge(existingEmployee);
                transaction.commit();
                return existingEmployee;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    // Delete employee
    public boolean deleteEmployee(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                transaction.begin();
                entityManager.remove(employee);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
