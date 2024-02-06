package com.example.schedulerapp_group11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ItemAdapter extends RecyclerView.Adapter<ItemVH> {

    ArrayList<TodoItem> list;
    Context viewContext;
    private OnDeleteListener onDeleteListener;
    private ItemChangedListener itemChangedListener;

    public ItemAdapter(ArrayList<TodoItem> list){
        this.list = list;
    }

    public interface OnDeleteListener{
        void onDelete(int position);
    }
    public interface ItemChangedListener{
        void itemChanged(ArrayList<TodoItem> list);
    }
    public void setItemChangedListener(ItemAdapter.ItemChangedListener itemChangedListener){
        this.itemChangedListener = itemChangedListener;
    }
    public void setOnDeleteListener(ItemAdapter.OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }


    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("hi v");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todoitem, parent, false);
        viewContext = parent.getContext();
        System.out.println("hi v");
        return new ItemVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, @SuppressLint("RecyclerView") int position) {
        System.out.println("hi v");
        holder.courseName.setText(list.get(position).getCourse());
        holder.itemName.setText(list.get(position).getName());
        holder.date.setText(list.get(position).getDueDate());
        if (list.get(position).getClass().equals(Exam.class)) {
            holder.location.setText(((Exam)(list.get(position))).getLocation());
        }
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
                dialog.setContentView(R.layout.task);
                Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                Button saveButton = dialog.findViewById(R.id.saveButton);
                EditText taskName = dialog.findViewById(R.id.taskView);
                EditText courseName = dialog.findViewById(R.id.editCourseName);
                EditText taskLocation = dialog.findViewById(R.id.editLocation);
                EditText taskTime = dialog.findViewById(R.id.editDateTime);
                EditText taskMonth = dialog.findViewById(R.id.editMonth);
                EditText taskDay = dialog.findViewById(R.id.editDay);
                EditText taskYear = dialog.findViewById(R.id.editYearText);
                Spinner spinner = dialog.findViewById(R.id.spinner);
                taskName.setText(list.get(position).getName());
                courseName.setText(list.get(position).getCourse());
                taskLocation.setText(list.get(position).getCourse());
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TodoItem change = list.get(position);
                        change.setName(courseName.getText().toString());
                        change.setDueDate(Integer.parseInt(String.valueOf(taskYear.getText())), Integer.parseInt(String.valueOf(taskMonth.getText())), Integer.parseInt(String.valueOf(taskDay.getText())));
                        if (itemChangedListener != null) {
                            itemChangedListener.itemChanged(list);
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
        return list.size();
    }
}

class ItemVH extends RecyclerView.ViewHolder{
    TextView itemName;
    TextView courseName;
    TextView date;
    TextView location;
    Button edit;
    Button delete;
    CheckBox checkBox;
    private ItemAdapter adapter;

    public ItemVH(@NonNull View itemView) {
        super(itemView);
        System.out.println("hi v");
        itemName = itemView.findViewById(R.id.textItem);
        courseName = itemView.findViewById(R.id.textCourse);
        date = itemView.findViewById(R.id.textDate);
        location = itemView.findViewById(R.id.textLocation);
        edit = itemView.findViewById(R.id.editButton);
        delete = itemView.findViewById(R.id.deleteButton);
        checkBox = itemView.findViewById(R.id.completedBox);
    }
    public ItemVH linkAdapter(ItemAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}