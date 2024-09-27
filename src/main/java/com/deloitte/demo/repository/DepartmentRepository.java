package com.deloitte.demo.repository;

import com.deloitte.demo.model.Department;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.deloitte.demo.config.PersistenceConfig;

import java.util.List;

public class DepartmentRepository {
    private EntityManager entityManager;

    public DepartmentRepository() {
        this.entityManager = PersistenceConfig.getEntityManagerFactory().createEntityManager();
    }

    public void save(Department department) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(department);
        transaction.commit();
    }

    public List<Department> getAllDepartments() {
        return entityManager.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    public Department getDepartmentById(int id) {
        return entityManager.find(Department.class, id);
    }

    public Department updateDepartment(int id, Department updatedDepartment) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Department department = entityManager.find(Department.class, id);
        if (department != null) {
            department.setName(updatedDepartment.getName());
            // Set other fields if necessary
            entityManager.merge(department);
        }
        transaction.commit();
        return department;
    }

    public boolean deleteDepartment(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Department department = entityManager.find(Department.class, id);
        if (department != null) {
            entityManager.remove(department);
            transaction.commit();
            return true;
        }
        transaction.commit();
        return false; // Department not found
    }
}
