package com.example.employeetracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.employeetracker.R;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.model.Employee;

import java.util.List;

/**
 * Created by aayu on 1/2/2017.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context context;
    List<Employee>employeeList;
    ItemUpdateAndDeleteListener listener;
    String flag;

    public EmployeeAdapter(Context context,List<Employee>employeeList,String flag)
    {
        this.context=context;
        listener= (ItemUpdateAndDeleteListener) context;
        this.employeeList=employeeList;
        this.flag=flag;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.employee_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
                Employee employee=employeeList.get(position);
                holder.name.setText(employee.getName());
                holder.email.setText(employee.getEmail());
                holder.phone.setText(employee.getPhone());
                holder.update.setTag(position);
if(flag!=null)
{
    if(flag.equals("2"))
    {
        holder.delete.setVisibility(View.GONE);
        holder.update.setText("Track Employee");

    }

}
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
        public EmployeeViewHolder(View itemView)
        {
            super(itemView);
            delete=(Button)itemView.findViewById(R.id.delete);
            update=(Button)itemView.findViewById(R.id.update);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            email=(TextView)itemView.findViewById(R.id.tv_email);
            phone=(TextView)itemView.findViewById(R.id.tv_phone);
        }
    }
}
