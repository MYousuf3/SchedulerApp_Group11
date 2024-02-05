package com.example.schedulerapp_group11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class CourseAdapter extends RecyclerView.Adapter<CourseVH> {

    ArrayList<Course> courses;
    Context viewContext;
    private OnDeleteListener onDeleteListener;
    private ItemChangedListener itemChangedListener;

    public CourseAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public interface OnDeleteListener{
        void onDelete(int position);
    }
    public interface ItemChangedListener{
        void itemChanged(ArrayList<Course> courses);
    }
    public void setItemChangedListener(ItemChangedListener itemChangedListener){
        this.itemChangedListener = itemChangedListener;
    }
    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course, parent, false);
        viewContext = parent.getContext();
        return new CourseVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, @SuppressLint("RecyclerView") int position) {
        holder.courseName.setText(courses.get(position).getCourseName());
        holder.professor.setText(courses.get(position).getProf());
        holder.days.setText(courses.get(position).getTime());
        holder.location.setText(courses.get(position).getRoom());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(viewContext);
                dialog.setContentView(R.layout.confirmation_dialog);
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                Button confirmButton = dialog.findViewById(R.id.confirmButton);
                Button cancelButton = dialog.findViewById(R.id.cancelButton);
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onDeleteListener != null) {
                            onDeleteListener.onDelete(position);
                        }
                        dialog.dismiss();
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(viewContext);
                dialog.setContentView(R.layout.editdialog);
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                Button saveButton = dialog.findViewById(R.id.saveButton);
                EditText className = dialog.findViewById(R.id.editCourseName);
                EditText prof = dialog.findViewById(R.id.editDateTime);
                EditText dateText = dialog.findViewById(R.id.editLocation);
                EditText locText = dialog.findViewById(R.id.editTextLoc);
                className.setText(courses.get(position).getCourseName());
                prof.setText(courses.get(position).getProf());
                dateText.setText(courses.get(position).getTime());
                locText.setText(courses.get(position).getRoom());

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Course change = courses.get(position);
                        change.setCourseName(className.getText().toString());
                        change.setProf(prof.getText().toString());
                        change.setTime(dateText.getText().toString());
                        change.setRoom(locText.getText().toString());
                        courses.set(position, change);
                        if (itemChangedListener != null) {
                            itemChangedListener.itemChanged(courses);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
class CourseVH extends RecyclerView.ViewHolder{

    TextView courseName;
    TextView professor;
    TextView days;
    TextView location;
    Button edit;
    Button delete;
    private CourseAdapter adapter;

    public CourseVH(@NonNull View itemView) {
        super(itemView);
        courseName = itemView.findViewById(R.id.textItem);
        professor = itemView.findViewById(R.id.textCourse);
        days = itemView.findViewById(R.id.textDate);
        location = itemView.findViewById(R.id.textLocation);
        edit = itemView.findViewById(R.id.editButton);
        delete = itemView.findViewById(R.id.deleteButton);
    }
    public CourseVH linkAdapter(CourseAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}
