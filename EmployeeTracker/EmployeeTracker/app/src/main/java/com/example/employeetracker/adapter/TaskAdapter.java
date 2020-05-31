package com.example.employeetracker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.employeetracker.R;

import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.TaskList;

import java.util.List;

/**
 * Created by aayu on 1/2/2017.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.EmployeeViewHolder> {

    Context context;
    List<TaskList>employeeList;

    ItemUpdateAndDeleteListener listener;
    public TaskAdapter(Context context, List<TaskList>employeeList)
    {
        this.context=context;
        this.employeeList=employeeList;
        listener=(ItemUpdateAndDeleteListener)context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
                TaskList employee=employeeList.get(position);
                holder.name.setText(employee.getTask());
                holder.email.setText(employee.getEmpName());
                holder.phone.setText(employee.getLastDate());
        if(employee.getStatus().equals("1"))
        {
            holder.mainHolder.setBackgroundColor(Color.GREEN);
            holder.update.setVisibility(View.GONE);

        }
        holder.update.setTag(position);
        holder.delete.setTag(position);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.updateItem((Integer)view.getTag());
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteItem((Integer)view.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email,phone;
        Button delete,update;
        LinearLayout mainHolder;
        public EmployeeViewHolder(View itemView)
        {
            super(itemView);
            delete=(Button)itemView.findViewById(R.id.delete);
            update=(Button)itemView.findViewById(R.id.update);
            mainHolder=(LinearLayout)itemView.findViewById(R.id.holder);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            email=(TextView)itemView.findViewById(R.id.tv_email);
            phone=(TextView)itemView.findViewById(R.id.tv_phone);
        }
    }
}
