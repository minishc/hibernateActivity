package org.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtility {
    private static SessionFactory sessionFactory;

    private HibernateUtility() {
        generateSessionFactory();
    }

    public static void generateSessionFactory() {
        if(sessionFactory == null) {
            Configuration cfg = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"));
            try {
                sessionFactory = cfg.buildSessionFactory();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            generateSessionFactory();
        }
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
    }
}
