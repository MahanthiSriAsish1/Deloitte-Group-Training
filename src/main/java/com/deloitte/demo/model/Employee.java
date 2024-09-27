package com.deloitte.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double salary;

    // Separate departmentId field
    @Column(name = "department_id", insertable = false, updatable = false)
    private Integer departmentId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        if (department != null) {
            this.departmentId = department.getId(); // Sync the departmentId field with the department object
        } else {
            this.departmentId = null; // Clear the departmentId if department is null
        }
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        if (departmentId != null) {
            this.department = new Department();
            this.department.setId(departmentId); // Sync the department object based on the departmentId
        } else {
            this.department = null;
        }
    }
}
