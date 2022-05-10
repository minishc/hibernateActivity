package org.hibernate.services;

import org.hibernate.SessionFactory;
import org.hibernate.dao.DepartmentDAO;
import org.hibernate.models.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService implements DepartmentDAO {

    @Override
    public List<Department> getAllDepartments() {
        List<Department> result = new ArrayList<>();
       // SessionFactory session =
        return null;
    }

    @Override
    public boolean addDepartment(Department dep) {
        return false;
    }

    @Override
    public boolean removeDepartment(int id) {
        return false;
    }

    @Override
    public boolean removeDepartment(Department dep) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
