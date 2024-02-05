package com.example.schedulerapp_group11.ui.dashboard;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedulerapp_group11.Course;
import com.example.schedulerapp_group11.CourseAdapter;
import com.example.schedulerapp_group11.ItemAdapter;
import com.example.schedulerapp_group11.R;
import com.example.schedulerapp_group11.TodoItem;
import com.example.schedulerapp_group11.databinding.FragmentDashboardBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment implements ItemAdapter.ItemChangedListener, ItemAdapter.OnDeleteListener{


    private FragmentDashboardBinding binding;
    private ArrayList<TodoItem> list;
    DashboardViewModel dashboardViewModel;
    ItemAdapter adapter;


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
        RecyclerView recyclerView = binding.recyclerView01;
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ItemAdapter(list);
        adapter.setOnDeleteListener(this);
        adapter.setItemChangedListener(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingButton = binding.floatingActionButton;
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(inflater.getContext());
                dialog.setContentView(R.layout.task);
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                Button saveButton = dialog.findViewById(R.id.saveButton);
                //EditText taskName = dialog.findViewById(R.id.edit);
                EditText courseName = dialog.findViewById(R.id.editCourseName);
                EditText dateText = dialog.findViewById(R.id.editDateTime);
                EditText locText = dialog.findViewById(R.id.editLocation);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //list.add(new TodoItem(taskName.getText().toString(), prof.getText().toString(), dateText.getText().toString(), locText.getText().toString()));
                        //adapter.notifyItemInserted(list.size() - 1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDelete(int position) {

    }

    @Override
    public void itemChanged(ArrayList<TodoItem> list) {

    }
}