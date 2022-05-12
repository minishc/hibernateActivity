package org.hibernate.dao;

import org.hibernate.models.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    boolean addDepartment(String name, String state);

    boolean addDepartment(Department department);
    Department removeDepartment(int id);
    boolean removeDepartment(Department dep);
    Department getDepartment(int id);
}
