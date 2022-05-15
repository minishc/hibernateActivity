package org.hibernate.services;

import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.dao.AddressDAO;
import org.hibernate.models.Address;
import org.hibernate.models.Employee;
import org.hibernate.util.HibernateUtility;

import java.util.List;
@Log
public class AddressService implements AddressDAO {
    @Override
    public List<Address> getAllAddresses() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        List<Address> addresses = session.createQuery("FROM Address", Address.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return addresses;
    }

    @Override
    public Address getAddressById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Address result = null;
        try {
            result = session.createQuery("FROM Address WHERE id = :id", Address.class)
                    .setParameter("id", id).getSingleResult();

        }
        catch(Exception exc) {
            log.info(exc.toString());
        }

        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Address createAddress(String addr1, String addr2, String City, int zip, Employee employee) {
        Address toAdd = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            toAdd = new Address(addr1, addr2, City, zip, employee);
            session.merge(toAdd);
            session.getTransaction().commit();
        }
        catch(Exception exc) {
            log.info(exc.toString());
        }

        session.close();
        return toAdd;
    }

    @Override
    public boolean createAddress(Address addr) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        boolean result = false;

        try {
            session.merge(addr);
            session.getTransaction().commit();
            result = true;
        }
        catch(Exception exc) {
            log.info(exc.toString());
        }

        return result;
    }
}
