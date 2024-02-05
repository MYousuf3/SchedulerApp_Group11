package com.example.schedulerapp_group11;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TodoItem {
    private Date due;
    private String memo;
    private boolean isDone;

    public TodoItem(int year, int month, int date, String m, boolean isDone) {
        // haha lol
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        due = cal.getTime();
        memo = m;
        this.isDone = isDone;
    }

    public TodoItem(int year, int month, int date, String m) {
        this(year, month, date, m, false);
    }

    public TodoItem(int year, int month, int date, int hour, int minute, String m, boolean isDone) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hour, minute);
        due = cal.getTime();
        memo = m;
    }

    public int daysTillDue() {
        Date curr = new Date();
        long diff = Math.min(due.getTime() - curr.getTime(), 0);
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getName() {
        return memo;
    }

    public void setName(String name) {
        memo = name;
    }

    public boolean isCompleted() {
        return isDone;
    }


    public void toggle() {
        isDone = !isDone;
    }

    public String getDueDate() {
        return due.toString();
    }

    public void setDueDate(int year, int month, int date, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hour, minute);
    }

    public void setDueDate(int year, int month, int date) {
        setDueDate(year, month, date, 0, 0);
    }

    public long getTime() {
        return due.getTime();
    }

    public static Comparator<TodoItem> earlyFirst() {
        return (a, b) -> (a.daysTillDue() - b.daysTillDue());
    }

    public static Comparator<TodoItem> laterFirst() {
        return (a, b) -> (b.daysTillDue() - a.daysTillDue());
    }

    @Override
    public boolean equals (Object it) {
        if (it == null || !this.getClass().equals(it.getClass())) {
            return false;
        }

        TodoItem item = (TodoItem) it;
        return this.memo.equals(item.memo) && this.due.equals(item.due) && this.isDone == item.isDone;
    }
}
