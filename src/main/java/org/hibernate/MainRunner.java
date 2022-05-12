package org.hibernate;

import lombok.extern.java.Log;
import org.hibernate.models.Department;
import org.hibernate.models.Employee;
import org.hibernate.models.Address;
import org.hibernate.models.Project;
import org.hibernate.query.Query;
import org.hibernate.services.DepartmentService;
import org.hibernate.services.EmployeeService;
import org.hibernate.services.ProjectService;
import org.hibernate.util.HibernateUtility;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
@Log
public class MainRunner {
    public static void main( String[] args ) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();

        EmployeeService es = new EmployeeService();
        ProjectService ps = new ProjectService();
        session.beginTransaction();
        Project newProject = new Project("Capstone");
        Employee employee1 = es.createEmployee(new Employee("Dan", 45000));
        Employee employee2 = es.createEmployee(new Employee("Jordan", 44999));
        session.persist(newProject);
        session.getTransaction().commit();

        ps.addEmployeeToProject(1, 1);
        ps.addEmployeeToProject(1, 2);

        Map<Project, List<Employee>> projectAndEmployees = ps.getAllProjectsAndEmployees();

        for(Project project : projectAndEmployees.keySet()) {
            log.info(project + " has employees " + projectAndEmployees.get(project).toString());
        }

        session.close();
        HibernateUtility.getSessionFactory().close();
    }
}
