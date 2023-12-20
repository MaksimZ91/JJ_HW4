package org.example;

import org.example.Entity.Courses;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();

        Courses firstCourse = new Courses("first", 12L);
        Courses secondCourse = new Courses("second", 15L);

        repository.add(firstCourse);
        repository.add(secondCourse);

        System.out.println(repository.findByID(Courses.class, 1));
        System.out.println(repository.findAll(Courses.class));

        repository.updateTitleByID(Courses.class, 1, "Five");

        System.out.println(repository.findByID(Courses.class, 1));
        repository.deleteByID(Courses.class, 1);
    }
}