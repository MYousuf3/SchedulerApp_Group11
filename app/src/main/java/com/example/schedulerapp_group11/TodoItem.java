package com.example.schedulerapp_group11;
import java.util.Comparator;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TodoItem {
    private Date due;
    private String memo;

    public TodoItem(int year, int month, int date, String m) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date);
        due = cal.getTime();
        memo = m;
    }

    public TodoItem(int year, int month, int date, int hour, int minute, String m) {
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

    public long getTime() {
        return due.getTime();
    }

    public Comparator<TodoItem> earlyFirst() {
        return (a, b) -> (a.daysTillDue() - b.daysTillDue());
    }
    public Comparator<TodoItem> laterFirst() {
        return (a, b) -> (b.daysTillDue() - a.daysTillDue());
    }

}
