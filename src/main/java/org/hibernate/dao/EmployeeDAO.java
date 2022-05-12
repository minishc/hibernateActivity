package org.hibernate.dao;

import org.hibernate.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee createEmployee(String name, double salary);
    Employee createEmployee(Employee employee);
    Employee addEmployeeToDepartment(Employee employee, int id);
}
