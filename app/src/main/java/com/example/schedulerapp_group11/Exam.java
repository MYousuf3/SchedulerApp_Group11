package com.example.schedulerapp_group11;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Exam extends TodoItem {
    String courseName;
    String location;
    int hour;
    int minute;
    public Exam (int year, int month, int day, int hour, int minute, String examName, String courseName, String location, boolean isCompleted) {
        super(year, month, day, hour, minute, examName);
        this.courseName = courseName;
        this.location = location;
        this.hour = hour;
        this.minute = minute;
    }
    @Override
    public String getCourse() {
        return courseName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {this.hour = hour;}

    public int getMinute(){return minute;}

    public void setMinute(int minute) {this.minute = minute;}


    public void setCourse(String className) {
        this.courseName = className;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String loc) {
        this.location = loc;
    }

    /*
    public static Comparator<Exam> sortByCourse() {
        return (a, b) -> (a.courseName.compareTo(b.courseName));
    }
     */

    @Override
    public boolean equals (Object it) {
        if (it == null || !this.getClass().equals(it.getClass())) {
            return false;
        }

        Exam item = (Exam) it;
        return super.equals(item) && this.courseName.equals(item.courseName) && this.location.equals(item.location);
    }
}