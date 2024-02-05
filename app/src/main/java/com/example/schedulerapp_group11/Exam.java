package com.example.schedulerapp_group11;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Exam extends TodoItem {
    String courseName;
    String location;
    public Exam (int year, int month, int day, int hour, int minute, String examName, String courseName, String location, boolean isCompleted) {
        super(year, month, day, hour, minute, examName, isCompleted);
        this.courseName = courseName;
        this.location = location;
    }
    public String getCourse() {
        return courseName;
    }


    public void setCourse(String className) {
        this.courseName = className;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String loc) {
        this.location = loc;
    }

    public Comparator<Exam> sortByCourse() {
        return (a, b) -> (a.courseName.compareTo(b.courseName));
    }

    @Override
    public boolean equals (Object it) {
        if (it == null || !this.getClass().equals(it.getClass())) {
            return false;
        }

        Exam item = (Exam) it;
        return super.equals(item) && this.courseName.equals(item.courseName) && this.location.equals(item.location);
    }
}