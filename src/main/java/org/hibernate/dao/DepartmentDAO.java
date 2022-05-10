package org.hibernate.dao;

import org.hibernate.models.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    boolean addDepartment(Department dep);
    boolean removeDepartment(int id);
    boolean removeDepartment(Department dep);

}
