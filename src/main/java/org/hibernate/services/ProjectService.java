package org.hibernate.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dao.ProjectDAO;
import org.hibernate.models.Employee;
import org.hibernate.models.Project;
import org.hibernate.util.HibernateUtility;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ProjectService implements ProjectDAO {

    @Override
    public Map<Project, List<Employee>> getAllProjectsAndEmployees() {
        Map<Project, List<Employee>> result = new HashMap<>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Project> projects = session.createQuery("FROM Project", Project.class).getResultList();

        for(Project project : projects) {
            List<Employee> employees = session.createNativeQuery("SELECT e.* FROM Project p JOIN Employee_Projects ep ON"+
                    " p.id = ep.project_id JOIN Employee AS e ON ep.employee_id = e.id", Employee.class).getResultList();
            result.put(project, employees);
        }
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void addEmployeeToProject(int projectId, int employeeId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Project project = session.createQuery("FROM Project WHERE id = :id", Project.class)
                    .setParameter("id", projectId).getSingleResult();
            Employee employee = session.createNamedQuery("getEmployeeById", Employee.class)
                    .setParameter("id", employeeId).getSingleResult();
            employee.addProject(project);
            session.merge(employee);
            session.getTransaction().commit();
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        session.close();
    }
}
