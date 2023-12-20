package org.example;

import org.example.Entity.Courses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connector {
    private static SessionFactory sessionFactory;

    public Connector() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Courses.class)
                .buildSessionFactory();
    }

    public Session newSession(){
        return sessionFactory.openSession();
    }

}