package com.example.schedulerapp_group11.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp_group11.Course;
import com.example.schedulerapp_group11.CourseAdapter;
import com.example.schedulerapp_group11.R;
import com.example.schedulerapp_group11.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements CourseAdapter.OnDeleteListener, CourseAdapter.ItemChangedListener{

    private FragmentHomeBinding binding;
    ArrayList<Course> courses;
    CourseAdapter adapter;
    HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        courses = new ArrayList<>();
        if (homeViewModel.getSavedCourses() != null) {
            courses = homeViewModel.getSavedCourses();
        }
        RecyclerView recyclerView = binding.recyclerView01;
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CourseAdapter(courses);
        adapter.setOnDeleteListener(this);
        adapter.setItemChangedListener(this);
        recyclerView.setAdapter(adapter);



        FloatingActionButton floatingButton = binding.floatingActionButton;
        floatingButton.setOnClickListener(view -> {
            Dialog dialog = new Dialog(inflater.getContext());
            dialog.setContentView(R.layout.dialog);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            Button saveButton = dialog.findViewById(R.id.saveButton);
            EditText className = dialog.findViewById(R.id.editCourseName);
            EditText prof = dialog.findViewById(R.id.editDateTime);
            EditText dateText = dialog.findViewById(R.id.editLocation);
            EditText locText = dialog.findViewById(R.id.editTextLoc);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (String.valueOf(className.getText()).equals("") ||
                            String.valueOf(prof.getText()).equals("") ||
                            String.valueOf(dateText.getText()).equals("") ||
                            String.valueOf(locText.getText()).equals("")) {
                        Toast.makeText(inflater.getContext(), "Invalid Entry", Toast.LENGTH_SHORT).show();
                    } else {
                        courses.add(new Course(className.getText().toString(), prof.getText().toString(), dateText.getText().toString(), locText.getText().toString()));
                        adapter.notifyItemInserted(courses.size() - 1);
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeViewModel.setSavedCourses(courses);
        binding = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDelete(int position) {
        courses.remove(position);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void itemChanged(ArrayList<Course> newCourses) {
        courses = newCourses;
        adapter.notifyDataSetChanged();
    }
}