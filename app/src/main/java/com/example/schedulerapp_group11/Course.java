package com.example.schedulerapp_group11;

public class Course {

    private String courseName;
    private String time;
    private String days;
    private String prof;
    private String section;
    private String room;

    public Course(String cN, String t, String d, String p, String s, String r) {
        courseName = cN;
        time = t;
        days = d;
        prof = p;
        section = s;
        room = r;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDays() {
        return days;
    }

    public String getProf() {
        return prof;
    }

    public String getRoom() {
        return room;
    }

    public String getSection() {
        return section;
    }

    public String getTime() {
        return time;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
