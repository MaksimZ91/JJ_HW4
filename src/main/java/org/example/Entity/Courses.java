package org.example.Entity;

import com.sun.istack.NotNull;

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
