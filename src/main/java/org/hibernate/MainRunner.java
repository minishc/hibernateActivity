package org.hibernate;

import lombok.extern.java.Log;
import org.hibernate.models.Department;
import org.hibernate.models.Employee;
import org.hibernate.models.Address;
import org.hibernate.query.Query;
import org.hibernate.services.DepartmentService;
import org.hibernate.util.HibernateUtility;

import java.util.List;

/**
 * Hello world!
 *
 */
@Log
public class MainRunner {
    public static void main( String[] args ) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Address> query = session.createNamedQuery("getAddressById", Address.class);
        List<Address> result = query.setParameter("id", 1).getResultList();

        for(Address address : result) {
            log.info(address.toString());
        }

        transaction.commit();
        session.close();
        HibernateUtility.close();
    }
}
