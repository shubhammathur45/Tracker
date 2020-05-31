package com.example.employeetracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.employeetracker.R;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.Login;

import java.util.List;

/**
 * Created by aayu on 1/2/2017.
 */
public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.EmployeeViewHolder> {

    Context context;
    List<Login>loginList;
    ItemUpdateAndDeleteListener listener;
    String path;


    public LoginAdapter(Context context, List<Login>loginsList,String path)
    {
        this.context=context;
        listener= (ItemUpdateAndDeleteListener) context;
        this.loginList=loginsList;
        this.path=path;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.login_item,parent,false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
                Login login=loginList.get(position);
                holder.name.setText(login.getEmpId());
                holder.email.setText(login.getLoginTime());
                holder.phone.setText(login.getLocation());
                Glide.with(context).load(path+"/"+login.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,email,phone;
        ImageView imageView;
        public EmployeeViewHolder(View itemView)
        {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img_icon);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            email=(TextView)itemView.findViewById(R.id.tv_email);
            phone=(TextView)itemView.findViewById(R.id.tv_phone);
        }
    }
}
