# Урок 4. Базы данных и инструменты взаимодействия с ними
Создайте базу данных (например, SchoolDB).  
В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.  
Настройте Hibernate для работы с вашей базой данных.  
Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.  
Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.  
Убедитесь, что каждая операция выполняется в отдельной транзакции.  

## Create
![add](https://github.com/MaksimZ91/JJ_HW4/assets/72209139/ad9c5727-9af2-44e8-ba3e-84e3e3ae8956)

## Read
![read](https://github.com/MaksimZ91/JJ_HW4/assets/72209139/ec6b8b69-6ea4-4b9c-a022-f3be17780b50)

## Update
![update](https://github.com/MaksimZ91/JJ_HW4/assets/72209139/f726f6df-c92a-4699-a785-89aa68ecef3b)

## Delete
![delete](https://github.com/MaksimZ91/JJ_HW4/assets/72209139/c085f4b3-9936-46f3-a885-f3495d3f2757)

## Main Class
```java
package org.example;

import org.example.Entity.Courses;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        Courses firstCourse = new Courses("first", 12L);
        Courses secondCourse = new Courses("second", 15L);

        repository.add(firstCourse);
        repository.add(secondCourse);

        System.out.println("==========================");
        System.out.println(repository.findByID(Courses.class, 1));
        System.out.println("==========================");
        System.out.println(repository.findAll(Courses.class));

        repository.updateTitleByID(Courses.class, 1, "Five");
        System.out.println("==========================");
        System.out.println(repository.findByID(Courses.class, 1));

        repository.deleteByID(Courses.class, 1);
    }
}
```

## Connector class
```java
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
```

## Repository class
```java
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
```

## Courses class
```java
package org.example.Entity;
import javax.persistence.*;

@Entity
@Table(name = "myDB.courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "titel")
    private String title;
    @Column(name = "duration")
    private long duration;
    public  Courses(){}
    public Courses(String title, long duration) {
        this.title = title;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
```




