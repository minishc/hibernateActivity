package org.hibernate.services;

import jakarta.persistence.NamedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dao.EmployeeDAO;
import org.hibernate.models.Department;
import org.hibernate.models.Employee;
import org.hibernate.query.Query;
import org.hibernate.util.HibernateUtility;

import java.util.List;

public class EmployeeService implements EmployeeDAO {
    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Query<Employee> query = session.createNamedQuery("getEmployeeById", Employee.class);
        query.setParameter("id", id);
        Employee employee = query.getSingleResult();
        session.close();
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    @Override
    public Employee addEmployeeToDepartment(Employee employee, int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Department department = session.createQuery("FROM Department WHERE id = :id", Department.class)
                .setParameter("id", id).getSingleResult();
        department.addEmployee(employee);
        transaction.commit();
        session.close();
        return employee;
    }

}