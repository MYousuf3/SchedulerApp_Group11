package com.example.schedulerapp_group11;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Exam extends TodoItem {
    String courseName;
    String location;
    public Exam (int year, int month, int day, int hour, int minute, String examName, String courseName, String location,boolean isCompleted) {
        super(year, month, day, hour, minute, examName, isCompleted);
        this.courseName = courseName;
        this.location = location;
    }

    public Comparator<Exam> sortByCourse() {
        return (a, b) -> (a.courseName.compareTo(b.courseName));
    }
}
/*
public class Exam {
    private String name;
    private Date date;
    private String course;
    private String time;
    private String location;

    public Exam(int year, int month, int day, int hour, int minute, String n, String c, String l) {
        name = n;
        course = c;
        location = l;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        date = cal.getTime();
    }
    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        date = cal.getTime();
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
 */
