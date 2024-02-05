package com.example.schedulerapp_group11;

public class Course {

    private String courseName;
    private String time;
    private String prof;
    private String room;

    public Course(String cN, String p, String t, String r) {
        courseName = cN;
        time = t;
        prof = p;
        room = r;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getProf() {
        return prof;
    }

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
