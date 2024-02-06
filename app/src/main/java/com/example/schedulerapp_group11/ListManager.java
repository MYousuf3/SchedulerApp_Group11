package com.example.schedulerapp_group11;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListManager {
    private ArrayList<TodoItem> backing;

    public ListManager(ArrayList<TodoItem> list) {
        backing = list;
    }

    public ArrayList<TodoItem> getList(){
        return backing;
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
        backing.sort(TodoItem.earlyFirst());
    }

    public void removeItem(int p) {
        backing.remove(p);
    }

    public ArrayList<TodoItem> getIncomplete() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (TodoItem t : backing) {
            if (!t.isCompleted()) {
                temp.add(t);
            }
        }

        return temp;
    }

    public ArrayList<TodoItem> getCourseRelated() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (TodoItem t : backing) {
            if (t.getClass() == Assignment.class || t.getClass() == Exam.class) {
                temp.add(t);
            }
        }
        // Collections.sort(temp, (a, b) -> a.getCourse().compare(b.getCourse()));
        temp.sort(new Comparator<TodoItem>() {
            public int compare(TodoItem t1, TodoItem t2) {
                return t1.getCourse().compareTo(t1.getCourse());
            }
        });
        return temp;
    }
}