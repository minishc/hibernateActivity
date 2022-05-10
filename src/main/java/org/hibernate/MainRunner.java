package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.models.Employee;
import org.hibernate.models.Address;
import java.io.File;
import java.util.List;

/**
 * Hello world!
 *
 */
public class MainRunner {
    private static SessionFactory sessionFactory;
    public static void main( String[] args ) {

        try {
            sessionFactory = new Configuration().configure(
                            new File("src/main/resources/hibernate.cfg.xml"))
                            .buildSessionFactory();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        Session session = sessionFactory.openSession();
        Transaction transact = session.beginTransaction();

        Employee e1 = new Employee("dan", 45000.0);
        Employee e2 = new Employee("joradan", 44999.0);
        Address a1 = new Address("123 st", "", "Chicago", 60601, e1);

        session.persist(e1);
        session.persist(e2);
        session.persist(a1);
        transact.commit();
        List<Employee> employees = session.createQuery("from Employee").list();
        System.out.println(employees);
        session.close();
    }
}
