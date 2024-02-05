package com.example.schedulerapp_group11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ItemAdapter extends RecyclerView.Adapter<ItemVH> {

    ArrayList<TodoItem> items;
    Context viewContext;
    private ItemAdapter.OnDeleteListener onDeleteListener;
    private ItemAdapter.ItemChangedListener itemChangedListener;

    public ItemAdapter(ArrayList<TodoItem> items){
        this.items = items;
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
                .inflate(R.layout.course, parent, false);
        viewContext = parent.getContext();
        return new ItemVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, @SuppressLint("RecyclerView") int position) {
        holder.courseName.setText(items.get(position).getCourse());
        holder.itemName.setText(items.get(position).getName());
        holder.date.setText(items.get(position).getDueDate());
        if (items.get(position).getClass().equals(Exam.class)) {
            holder.location.setText(((Exam)(items.get(position))).getLocation());
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

    }

    @Override
    public int getItemCount() {
        return 0;
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