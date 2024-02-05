package com.example.schedulerapp_group11;
import java.util.ArrayList;
import java.util.Collections;

public class ListManager {
    ArrayList<TodoItem> backing;

    public ListManager() {
        backing = new ArrayList<>();
    }


    public void addTask(int year, int month, int date, String m) {
        TodoItem t = new TodoItem(year, month, date, m);
        backing.add(t);
        Collections.sort(backing, TodoItem.earlyFirst());
    }

    public void addAssignment(int year, int month, int day, int hour, int minute, String title, String courseName) {
        Assignment a = new Assignment(year, month, day, hour, minute, title, courseName, false);
        backing.add(a);
        Collections.sort(backing, TodoItem.earlyFirst());
    }

    public void addExam(int year, int month, int day, int hour, int minute, String examName, String courseName, String location) {
        Exam e = new Exam(year, month, day, hour, minute, examName, courseName, location, false);
        backing.add(e);
        Collections.sort(backing, TodoItem.earlyFirst());
    }
}