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
import com.example.employeetracker.activities.EmpTaskList;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.listeners.SubmitTaskListener;
import com.example.employeetracker.model.EmployeeTaskList;
import com.example.employeetracker.model.TaskList;

import java.util.List;

/**
 * Created by aayu on 1/2/2017.
 */
public class EmpTaskAdapter extends RecyclerView.Adapter<EmpTaskAdapter.EmployeeViewHolder> {

    Context context;
    List<EmployeeTaskList>employeeList;
    SubmitTaskListener submitTaskListener;


    public EmpTaskAdapter(Context context, List<EmployeeTaskList>employeeList)
    {
        this.context=context;
        this.employeeList=employeeList;
        submitTaskListener=(SubmitTaskListener)context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.employee_task,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
                EmployeeTaskList employee=employeeList.get(position);
                if(employee.getStatus().equals("0"))
                {
                    holder.view.setBackgroundColor(Color.RED);
                }
                else
                {
                    holder.view.setBackgroundColor(Color.GREEN);
                    holder.update.setVisibility(View.GONE);
                }
                holder.name.setText(employee.getTask());
                holder.location.setText(employee.getTaskLocation());
                holder.date.setText(employee.getLastDate());
        holder.update.setTag(position);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitTaskListener.submitTask((Integer)view.getTag());
                //listener.updateItem((Integer)view.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout view;
        TextView name,location,date;
        Button update;
        public EmployeeViewHolder(View itemView)
        {
            super(itemView);
            update=(Button)itemView.findViewById(R.id.submit);
            view=(LinearLayout)itemView.findViewById(R.id.mainlayout);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            location=(TextView)itemView.findViewById(R.id.tv_location);
            date=(TextView)itemView.findViewById(R.id.tv_date);
        }
    }
}
