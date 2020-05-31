package com.example.employeetracker.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmpTaskListResponse {

@SerializedName("task_list")
@Expose
private List<EmployeeTaskList> taskList = null;
@SerializedName("msg")
@Expose
private String msg;

public List<EmployeeTaskList> getTaskList() {
return taskList;
}

public void setTaskList(List<EmployeeTaskList> taskList) {
this.taskList = taskList;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}