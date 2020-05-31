package com.example.employeetracker.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminTaskList {

@SerializedName("task_list")
@Expose
private List<TaskList> taskList = null;
@SerializedName("msg")
@Expose
private String msg;

public List<TaskList> getTaskList() {
return taskList;
}

public void setTaskList(List<TaskList> taskList) {
this.taskList = taskList;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}
