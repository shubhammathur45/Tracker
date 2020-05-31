package com.example.employeetracker.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class EmployeApiResponse {

    @SerializedName("employee_list")
    @Expose
    private List<Employee> employeeList = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}



