package edu.hw10.Task1.classes;

import edu.hw10.Task1.annotations.Min;

public class University {
    public final String title;
    public final String principal;
    public final int studentCount;
    public final Person student;

    public University(String title, String principal, @Min(0) int studentCount, Person student) {
        this.title = title;
        this.principal = principal;
        this.studentCount = studentCount;
        this.student = student;
    }

}
