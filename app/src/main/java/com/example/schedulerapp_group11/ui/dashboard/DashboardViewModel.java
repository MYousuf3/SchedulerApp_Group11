package com.example.schedulerapp_group11.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulerapp_group11.Course;
import com.example.schedulerapp_group11.TodoItem;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private ArrayList<TodoItem> savedItems;


    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is to-do list fragment");
    }
    public void setSavedItems(ArrayList<TodoItem> savedItems) {
        this.savedItems = savedItems;
    }

    public ArrayList<TodoItem> getsSavedItems() {
        return savedItems;
    }

    public LiveData<String> getText() {
        return mText;
    }
}