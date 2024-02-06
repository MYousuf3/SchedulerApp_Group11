package com.example.schedulerapp_group11;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TodoItem {
    private Date due;
    private String memo;
    private boolean isDone;
    int hour;
    int minute;
    int year;
    int month;
    int day;

    public TodoItem(int year, int month, int date, String m, boolean isDone) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);
        this.year = year;
        this.month = month;
        this.day = date;
        due = cal.getTime();
        memo = m;
        this.isDone = isDone;
    }

    public TodoItem(int year, int month, int date, String m) {
        this(year, month, date, m, false);
    }

    public TodoItem(int year, int month, int date, int hour, int minute, String m) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hour, minute);
        this.hour = hour;
        this.minute = minute;
        due = cal.getTime();
        memo = m;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
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
    public void setCompleted(boolean newVal){
        isDone = newVal;
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
        due = cal.getTime();
    }

    public void setDueDate(int year, int month, int date) {
        setDueDate(year, month, date, hour, minute);
    }

    public long getTime() {
        return due.getTime();
    }

    public static Comparator<TodoItem> earlyFirst() {
        return (a, b) -> (int) TimeUnit.DAYS.convert((a.getTime() - b.getTime()), TimeUnit.MILLISECONDS);
    }

    public static Comparator<TodoItem> laterFirst() {
        return (a, b) -> (int) TimeUnit.DAYS.convert((b.getTime() - a.getTime()), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean equals (Object it) {
        if (it == null || !this.getClass().equals(it.getClass())) {
            return false;
        }

        TodoItem item = (TodoItem) it;
        return this.memo.equals(item.memo) && this.due.equals(item.due) && this.isDone == item.isDone;
    }

    public String getCourse() {
        return "";
    }
}
