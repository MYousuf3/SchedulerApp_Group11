package com.example.schedulerapp_group11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todoitem, parent, false);
        viewContext = parent.getContext();
        return new ItemVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, @SuppressLint("RecyclerView") int position) {
        holder.courseName.setText(list.get(position).getCourse());
        holder.itemName.setText(list.get(position).getName());
        holder.date.setText(list.get(position).getDueDate());
        holder.checkBox.setChecked(list.get(position).isCompleted());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setCompleted(isChecked);
                if (itemChangedListener != null) {
                    itemChangedListener.itemChanged(list);
                }
            }
        });
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
                if (list.get(position).getClass() == Exam.class) {
                    dialog.setContentView(R.layout.examedit);
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
                    taskName.setText(list.get(position).getName());
                    courseName.setText(list.get(position).getCourse());
                    taskLocation.setText(list.get(position).getCourse());
                    taskHour.setText(String.valueOf(((Exam)list.get(position)).getHour()));
                    taskMinute.setText(String.valueOf(((Exam)list.get(position)).getMinute()));
                    taskDay.setText(String.valueOf((list.get(position)).getDay()));
                    taskMonth.setText(String.valueOf((list.get(position)).getMonth()));
                    taskYear.setText(String.valueOf((list.get(position)).getYear()));
                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (String.valueOf(taskYear.getText()).equals("") ||
                                    String.valueOf(taskMonth.getText()).equals("") ||
                                    String.valueOf(taskDay.getText()).equals("") ||
                                    String.valueOf(taskHour.getText()).equals("") ||
                                    String.valueOf(taskMinute.getText()).equals("") ||
                                    String.valueOf(taskName.getText()).equals("") ||
                                    String.valueOf(courseName.getText()).equals("") ||
                                    String.valueOf(taskLocation.getText()).equals("")) {
                                Toast.makeText(viewContext, "Invalid Entry", Toast.LENGTH_SHORT).show();
                            } else {
                                Exam change = (Exam) list.get(position);
                                change.setName(taskName.getText().toString());
                                change.setCourse(courseName.getText().toString());
                                change.setDueDate(Integer.parseInt(String.valueOf(taskYear.getText())),
                                        Integer.parseInt(String.valueOf(taskMonth.getText())),
                                        Integer.parseInt(String.valueOf(taskDay.getText())),
                                        Integer.parseInt(String.valueOf(taskHour.getText())),
                                        Integer.parseInt(String.valueOf(taskMinute.getText())));
                                change.setLocation(taskLocation.getText().toString());
                                change.setHour(Integer.parseInt(taskHour.getText().toString()));
                                change.setMinute(Integer.parseInt(taskMinute.getText().toString()));
                                change.setMonth(Integer.parseInt(taskMonth.getText().toString()));
                                change.setDay(Integer.parseInt(taskDay.getText().toString()));
                                change.setYear(Integer.parseInt(taskYear.getText().toString()));
                                if (itemChangedListener != null) {
                                    itemChangedListener.itemChanged(list);
                                }
                            }
                            dialog.dismiss();
                        }
                    });
                } else if (list.get(position).getClass() == Assignment.class) {
                    Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setContentView(R.layout.assignmentedit);
                    dialog.setCancelable(false);
                    Button saveButton = dialog.findViewById(R.id.saveButton);
                    EditText taskName = dialog.findViewById(R.id.taskView);
                    EditText courseName = dialog.findViewById(R.id.editCourseName);
                    EditText taskMonth = dialog.findViewById(R.id.editMonth);
                    EditText taskDay = dialog.findViewById(R.id.editDay);
                    EditText taskYear = dialog.findViewById(R.id.editYearText);
                    taskName.setText(list.get(position).getName());
                    courseName.setText(list.get(position).getCourse());
                    taskDay.setText(String.valueOf((list.get(position)).getDay()));
                    taskMonth.setText(String.valueOf((list.get(position)).getMonth()));
                    taskYear.setText(String.valueOf((list.get(position)).getYear()));
                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (String.valueOf(taskYear.getText()).equals("") ||
                                    String.valueOf(taskMonth.getText()).equals("") ||
                                    String.valueOf(taskDay.getText()).equals("") ||
                                    String.valueOf(taskName.getText()).equals("") ||
                                    String.valueOf(courseName.getText()).equals("")) {
                                Toast.makeText(viewContext, "Invalid Entry", Toast.LENGTH_SHORT).show();
                            } else {
                                Assignment change = (Assignment) list.get(position);
                                change.setName(taskName.getText().toString());
                                change.setCourse(courseName.getText().toString());
                                change.setDueDate(Integer.parseInt(String.valueOf(taskYear.getText())), Integer.parseInt(String.valueOf(taskMonth.getText())), Integer.parseInt(String.valueOf(taskDay.getText())));
                                change.setMonth(Integer.parseInt(taskMonth.getText().toString()));
                                change.setDay(Integer.parseInt(taskDay.getText().toString()));
                                change.setYear(Integer.parseInt(taskYear.getText().toString()));
                                if (itemChangedListener != null) {
                                    itemChangedListener.itemChanged(list);
                                }
                            }
                            dialog.dismiss();
                        }
                    });
                } else {
                    Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setContentView(R.layout.taskedit);
                    dialog.setCancelable(false);
                    Button saveButton = dialog.findViewById(R.id.saveButton);
                    EditText taskName = dialog.findViewById(R.id.taskView);
                    EditText taskMonth = dialog.findViewById(R.id.editMonth);
                    EditText taskDay = dialog.findViewById(R.id.editDay);
                    EditText taskYear = dialog.findViewById(R.id.editYearText);
                    taskDay.setText(String.valueOf((list.get(position)).getDay()));
                    taskMonth.setText(String.valueOf((list.get(position)).getMonth()));
                    taskYear.setText(String.valueOf((list.get(position)).getYear()));
                    taskName.setText(list.get(position).getName());

                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (String.valueOf(taskYear.getText()).equals("") ||
                                    String.valueOf(taskMonth.getText()).equals("") ||
                                    String.valueOf(taskDay.getText()).equals("") ||
                                    String.valueOf(taskName.getText()).equals("")) {
                                Toast.makeText(viewContext, "Invalid Entry", Toast.LENGTH_SHORT).show();
                            } else {
                                TodoItem change = list.get(position);
                                change.setName(taskName.getText().toString());
                                change.setDueDate(Integer.parseInt(String.valueOf(taskYear.getText())), Integer.parseInt(String.valueOf(taskMonth.getText())), Integer.parseInt(String.valueOf(taskDay.getText())));
                                change.setMonth(Integer.parseInt(taskMonth.getText().toString()));
                                change.setDay(Integer.parseInt(taskDay.getText().toString()));
                                change.setYear(Integer.parseInt(taskYear.getText().toString()));
                                if (itemChangedListener != null) {
                                    itemChangedListener.itemChanged(list);
                                }
                            }
                            dialog.dismiss();
                        }
                        });
                    }
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
    ConstraintLayout constraintLayout;
    private ItemAdapter adapter;

    public ItemVH(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.textItem);
        courseName = itemView.findViewById(R.id.textCourse);
        date = itemView.findViewById(R.id.textDate);
        location = itemView.findViewById(R.id.textLocation);
        edit = itemView.findViewById(R.id.editButton);
        delete = itemView.findViewById(R.id.deleteButton);
        checkBox = itemView.findViewById(R.id.completedBox);
        constraintLayout = itemView.findViewById(R.id.cLayout);
    }
    public ItemVH linkAdapter(ItemAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}