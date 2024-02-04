package com.example.schedulerapp_group11;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseVH> {

    ArrayList<Course> items;
    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
class CourseVH extends RecyclerView.ViewHolder{

    TextView courseName;
    TextView professor;
    TextView days;
    TextView location;
    Button edit;
    private CourseAdapter adapter;

    public CourseVH(@NonNull View itemView) {
        super(itemView);
        courseName = itemView.findViewById(R.id.textClass);
        professor = itemView.findViewById(R.id.textProf);
        days = itemView.findViewById(R.id.textDays);
        location = itemView.findViewById(R.id.textLocation);
        edit = itemView.findViewById(R.id.editButton);
        edit.setOnClickListener(view -> {

        });
    }
    public CourseVH linkAdapter(CourseAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
