package org.example;

import org.example.Entity.Courses;
import org.hibernate.Session;

import java.util.List;


public class Repository {
    private static Connector connector;

    public Repository() {
        this.connector = new Connector();
    }

    public  void add(Object ob) {
        try (Session session = connector.newSession()) {
            session.beginTransaction();
            session.save(ob);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object findByID(Class clazz, int id) {
        try (Session session = connector.newSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findAll(Class clazz) {
        try (Session session = connector.newSession()) {
            return session.createCriteria(clazz).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTitleByID(Class clazz, int id, String data) {
        try (Session session = connector.newSession()) {
            session.beginTransaction();
            Courses result = (Courses) session.get(clazz, id);
            result.setTitle(data);
            session.update(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteByID(Class clazz, int id) {
        try (Session session = connector.newSession()) {
            session.beginTransaction();
            Courses result = (Courses) session.get(clazz, id);
            session.delete(result);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}
