package com.example.schedulerapp_group11;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter {
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
}
