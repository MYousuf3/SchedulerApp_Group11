package com.example.schedulerapp_group11;

public class Exam {
    private String name;
    private String date;
    private String course;
    private String time;
    private String location;

    public Exam(String n, String d, String c, String t, String l) {
        name = n;
        date = d;
        course = c;
        time = t;
        location = l;
    }
    public String getTime() {
        return time;
    }
    public String getCourse() {
        return course;
    }
    public String getDate() {
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
