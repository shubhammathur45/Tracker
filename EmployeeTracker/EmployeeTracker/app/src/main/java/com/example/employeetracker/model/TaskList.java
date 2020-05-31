package com.example.employeetracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskList implements Parcelable{

@SerializedName("id")
@Expose
private String id;
@SerializedName("emp_name")
@Expose
private String empName;
@SerializedName("emp_email")
@Expose
private String empEmail;
@SerializedName("task")
@Expose
private String task;
@SerializedName("task_location")
@Expose
private String taskLocation;
@SerializedName("status")
@Expose
private String status;
@SerializedName("last_date")
@Expose
private String lastDate;

    protected TaskList(Parcel in) {
        id = in.readString();
        empName = in.readString();
        empEmail = in.readString();
        task = in.readString();
        taskLocation = in.readString();
        status = in.readString();
        lastDate = in.readString();
    }

    public static final Creator<TaskList> CREATOR = new Creator<TaskList>() {
        @Override
        public TaskList createFromParcel(Parcel in) {
            return new TaskList(in);
        }

        @Override
        public TaskList[] newArray(int size) {
            return new TaskList[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getEmpName() {
return empName;
}

public void setEmpName(String empName) {
this.empName = empName;
}

public String getEmpEmail() {
return empEmail;
}

public void setEmpEmail(String empEmail) {
this.empEmail = empEmail;
}

public String getTask() {
return task;
}

public void setTask(String task) {
this.task = task;
}

public String getTaskLocation() {
return taskLocation;
}

public void setTaskLocation(String taskLocation) {
this.taskLocation = taskLocation;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getLastDate() {
return lastDate;
}

public void setLastDate(String lastDate) {
this.lastDate = lastDate;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(empName);
        parcel.writeString(empEmail);
        parcel.writeString(task);
        parcel.writeString(taskLocation);
        parcel.writeString(status);
        parcel.writeString(lastDate);
    }
}