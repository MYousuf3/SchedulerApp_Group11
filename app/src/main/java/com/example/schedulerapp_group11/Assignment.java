package com.example.schedulerapp_group11;

import java.util.Comparator;

public class Assignment extends TodoItem {
    private String name;
    private String courseName;
    private String dueDate;

    public Assignment(int year, int month, int day, int hour, int minute, String title, String courseName, boolean completed) {
        super(year, month, day, hour, minute, title, completed);
        this.courseName = courseName;
    }

    public String getCourse() {
        return courseName;
    }


    public void setCourse(String className) {
        this.courseName = className;
    }

    public Comparator<Assignment> sortByCourse() {
        return (a, b) -> (a.courseName.compareTo(b.courseName));
    }


}
