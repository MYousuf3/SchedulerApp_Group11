package com.example.schedulerapp_group11;

public class Assignment {
     String name;
     String classNumber;
     String dueDate;
     boolean isCompleted;
     static int numAssignments = 0;

    public Assignment(String n, String cN, String dD) {
        name = n;
        classNumber = cN;
        dueDate = dD;
        isCompleted = false;
        numAssignments++;
    }

    public String getName(){
        return name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String getDueDate() {
        return dueDate;
    }
    public void complete() {
        isCompleted = true;
        numAssignments--;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
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
