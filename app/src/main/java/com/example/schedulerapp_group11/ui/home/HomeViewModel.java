package com.example.schedulerapp_group11.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedulerapp_group11.Course;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private ArrayList<Course> savedCourses;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        savedCourses = new ArrayList<>();
        mText.setValue("This is course fragment");
    }

    public void setSavedCourses(ArrayList<Course> savedCourses) {
        this.savedCourses = savedCourses;
    }

    public ArrayList<Course> getSavedCourses() {
        return savedCourses;
    }

    public LiveData<String> getText() {
        return mText;
    }
}