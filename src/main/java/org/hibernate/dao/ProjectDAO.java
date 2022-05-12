package org.hibernate.dao;

import org.hibernate.models.Employee;
import org.hibernate.models.Project;

import java.util.List;
import java.util.Map;

public interface ProjectDAO {
    public Map<Project, List<Employee>> getAllProjectsAndEmployees();

    public void addEmployeeToProject(int projectId, int employeeId);
}
