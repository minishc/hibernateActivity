package org.hibernate.dao;

import org.hibernate.models.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    boolean addDepartment(String name, String state);
    Department removeDepartment(int id);
    Department removeDepartment(Department dep);
    Department getDepartment(int id);
}
