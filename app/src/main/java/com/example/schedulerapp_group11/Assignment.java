package com.example.schedulerapp_group11;

public class Assignment extends TodoItem {
    private String name;
    private String course;
    private String dueDate;


    public Assignment(int year, int month, int day, int hour, int minute, String title, String courseName, boolean completed) {
        super(year, month, day, hour, minute, title, completed);
        this.course = courseName;
    }

    public String getName(){
        return name;
    }

    public String getClassNumber() {
        return course;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setClassNumber(String classNumber) {
        this.course = classNumber;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
