package org.hibernate.services;

import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dao.DepartmentDAO;
import org.hibernate.models.Department;
import org.hibernate.util.HibernateUtility;

import java.util.List;
@Log
public class DepartmentService implements DepartmentDAO {

    @Override
    public List<Department> getAllDepartments() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        List<Department> departments = session.createQuery("FROM Department", Department.class).list();
        session.close();
        return departments;
    }

    @Override
    public boolean addDepartment(String name, String state) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        boolean result = false;
        try {
            Transaction transaction = session.beginTransaction();
            Department department = new Department(name, state);
            session.persist(department);
            transaction.commit();
            result = true;
        }
        catch(Exception exc) {
            result = false;
            log.info(exc.toString());
        }
        session.close();
        return result;
    }

    @Override
    public boolean addDepartment(Department department) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        boolean result = true;
        try {
            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception exc) {
            result = false;
        }
        session.close();
        return result;
    }

    @Override
    public Department removeDepartment(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Department department = null;
        try {
            Transaction transaction = session.beginTransaction();
            department = session.get(Department.class, id);
            session.remove(department);
            transaction.commit();
        }
        catch(Exception exc) {
            log.info(exc.toString());
            throw exc;
        }
        session.close();
        return department;
    }

    @Override
    public boolean removeDepartment(Department dep) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Department department = dep;
        boolean result = false;
        try {
            Transaction transaction = session.beginTransaction();
            session.remove(department);
            transaction.commit();
            result = true;
        }
        catch (Exception exc) {
            log.info(exc.toString());
            throw exc;
        }
        session.close();
        return result;
    }

    @Override
    public Department getDepartment(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Department department = session.get(Department.class, id);
        session.close();
        return department;
    }

}
