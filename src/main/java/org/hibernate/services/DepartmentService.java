package org.hibernate.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dao.DepartmentDAO;
import org.hibernate.models.Department;
import org.hibernate.util.HibernateUtility;


import java.util.List;

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
            exc.printStackTrace();
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
            if(department != null) {
                session.remove(department);
            }
            transaction.commit();
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        session.close();
        return department;
    }

    @Override
    public Department removeDepartment(Department dep) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Department department = dep;
        try {
            Transaction transaction = session.beginTransaction();
            session.remove(department);
            transaction.commit();
        }
        catch(Exception exc) {
            exc.printStackTrace();
            department = null;
        }
        session.close();
        return department;
    }

    @Override
    public Department getDepartment(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Department department = session.get(Department.class, id);
        session.close();
        return department;
    }
}
