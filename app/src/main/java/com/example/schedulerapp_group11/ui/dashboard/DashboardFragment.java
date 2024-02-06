package com.example.schedulerapp_group11.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp_group11.Assignment;
import com.example.schedulerapp_group11.Exam;
import com.example.schedulerapp_group11.ItemAdapter;
import com.example.schedulerapp_group11.ListManager;
import com.example.schedulerapp_group11.R;
import com.example.schedulerapp_group11.TodoItem;
import com.example.schedulerapp_group11.databinding.FragmentDashboardBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment implements ItemAdapter.ItemChangedListener, ItemAdapter.OnDeleteListener{


    private FragmentDashboardBinding binding;
    ArrayList<TodoItem> list;
    DashboardViewModel dashboardViewModel;
    ItemAdapter adapter;
    String choice;
    String filterChoice;
    ListManager lm;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        list = new ArrayList<>();
        if (dashboardViewModel.getsSavedItems() != null) {
            list = dashboardViewModel.getsSavedItems();
        }
        RecyclerView recyclerView = binding.recyclerViewList;
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        lm = new ListManager(list);
        adapter = new ItemAdapter(lm.getList());
        adapter.setOnDeleteListener(this);
        adapter.setItemChangedListener(this);
        recyclerView.setAdapter(adapter);

        Spinner spinnerFilter = binding.spinner2;
        Button filter = binding.buttonFilter;
        filterChoice = "";
        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterChoice = (String) spinnerFilter.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                if (filterChoice.equals("Course")) {
                    list = lm.getCourseRelated();
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                if (filterChoice.equals("Incomplete")) {
                    list = lm.getIncomplete();
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                if (filterChoice.equals("Due Date")) {
                    list = lm.revert();
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        FloatingActionButton floatingButton = binding.floatingActionButton;
        floatingButton.setOnClickListener(view -> {

            Dialog dialog = new Dialog(inflater.getContext());
            dialog.setContentView(R.layout.task);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            Button saveButton = dialog.findViewById(R.id.saveButton);
            EditText taskName = dialog.findViewById(R.id.taskView);
            EditText courseName = dialog.findViewById(R.id.editCourseName);
            EditText taskLocation = dialog.findViewById(R.id.editLocation);
            EditText taskHour = dialog.findViewById(R.id.editDateTime);
            EditText taskMinute = dialog.findViewById(R.id.minute);
            EditText taskMonth = dialog.findViewById(R.id.editMonth);
            EditText taskDay = dialog.findViewById(R.id.editDay);
            EditText taskYear = dialog.findViewById(R.id.editYearText);

            Spinner spinner = dialog.findViewById(R.id.spinner);
            choice = "";
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    choice = (String) spinner.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View v) {
                    if (choice.equals("Exam")) {
                        lm.addExam(
                                Integer.parseInt(String.valueOf(taskYear.getText())),
                                Integer.parseInt(String.valueOf(taskMonth.getText())) - 1,
                                Integer.parseInt(String.valueOf(taskDay.getText())),
                                Integer.parseInt(String.valueOf(taskHour.getText())),
                                Integer.parseInt(String.valueOf(taskMinute.getText())),
                                String.valueOf(taskName.getText()),
                                String.valueOf(courseName.getText()),
                                String.valueOf(taskLocation.getText())
                                );
                    } else if (choice.equals("Assignment")) {
                        lm.addAssignment(
                                Integer.parseInt(String.valueOf(taskYear.getText())),
                                Integer.parseInt(String.valueOf(taskMonth.getText())),
                                Integer.parseInt(String.valueOf(taskDay.getText())),
                                23,
                                59,
                                String.valueOf(taskName.getText()),
                                String.valueOf(courseName.getText())
                        );
                    } else {
                        lm.addTask(
                                Integer.parseInt(String.valueOf(taskYear.getText())),
                                Integer.parseInt(String.valueOf(taskMonth.getText())),
                                Integer.parseInt(String.valueOf(taskDay.getText())),
                                String.valueOf(taskName.getText())
                        );
                    }
                    adapter.notifyDataSetChanged();
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
        dashboardViewModel.setSavedItems(list);
        binding = null;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDelete(int position) {
        lm.removeItem(position);
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void itemChanged(ArrayList<TodoItem> newList) {
        list = newList;
        adapter.notifyDataSetChanged();
    }
}