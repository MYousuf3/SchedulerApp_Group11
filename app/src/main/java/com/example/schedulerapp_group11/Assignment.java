package com.example.schedulerapp_group11;

public class Assignment {
    private String name;
    private String course;
    private String dueDate;
    private boolean isCompleted;
    private static int numAssignments = 0;

    public Assignment(String n, String cN, String dD) {
        name = n;
        course = cN;
        dueDate = dD;
        isCompleted = false;
        numAssignments++;
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
    public void complete() {
        isCompleted = true;
        numAssignments--;
    }

    public void setClassNumber(String classNumber) {
        this.course = classNumber;
    }

    public static int getNumAssignments() {
        return numAssignments;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }
}
