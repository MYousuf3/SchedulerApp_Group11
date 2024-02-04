package com.example.schedulerapp_group11.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp_group11.Course;
import com.example.schedulerapp_group11.CourseAdapter;
import com.example.schedulerapp_group11.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("Blank", "", "", "", "", ""));
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        RecyclerView recyclerView = binding.recyclerView01;
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        CourseAdapter adapter = new CourseAdapter(courses);
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingButton = binding.floatingActionButton;
        floatingButton.setOnClickListener(view -> {
            courses.add(new Course("Blank", "", "", "", "", ""));
            adapter.notifyItemInserted(courses.size() - 1);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}