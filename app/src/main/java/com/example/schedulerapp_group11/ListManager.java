package com.example.schedulerapp_group11;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListManager {
    private ArrayList<TodoItem> backing;
    private ArrayList<TodoItem> unused;

    public ListManager(ArrayList<TodoItem> list) {
        backing = list;
        unused = new ArrayList<>();
    }

    public ArrayList<TodoItem> getList(){
        return backing;
    }

    public void add(ArrayList<TodoItem> list, TodoItem item) {
        list.add(item);
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
        revert();
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (backing.get(i).isCompleted()) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }

        return backing;
    }

    public ArrayList<TodoItem> getComplete() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (!backing.get(i).isCompleted()) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }

        return backing;
    }

    public ArrayList<TodoItem> getAssignments() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (backing.get(i).getClass() != Assignment.class) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }

        return backing;
    }

    public ArrayList<TodoItem> getExams() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (backing.get(i).getClass() != Exam.class) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }

        return backing;
    }

    public ArrayList<TodoItem> getTasks() {
        ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (backing.get(i).getClass() != Exam.class && backing.get(i).getClass() != Assignment.class) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }

        return backing;
    }

    public ArrayList<TodoItem> getCourseRelated() {
        revert();
        // ArrayList<TodoItem> temp = new ArrayList<>();
        for (int i = 0; i < backing.size(); i++) {
            if (backing.get(i).getClass() != Assignment.class && backing.get(i).getClass() != Exam.class) {
                unused.add(backing.get(i));
                backing.remove(i);
                i--;
            }
        }
        // Collections.sort(temp, (a, b) -> a.getCourse().compare(b.getCourse()));
        backing.sort((t1, t2) -> t1.getCourse().compareTo(t2.getCourse()));
        return backing;
    }
    public ArrayList<TodoItem> revert() {
        for (int i = unused.size() - 1; i >= 0; i--) {
            backing.add(unused.get(i));
            unused.remove(i);
        }
        backing.sort(TodoItem.earlyFirst());
        return backing;
    }
}