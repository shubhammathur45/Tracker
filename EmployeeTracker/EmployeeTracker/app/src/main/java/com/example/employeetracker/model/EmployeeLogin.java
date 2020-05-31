package com.example.employeetracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeLogin {

@SerializedName("uid")
@Expose
private String uid;
@SerializedName("msg")
@Expose
private String msg;

public String getUid() {
return uid;
}

public void setUid(String uid) {
this.uid = uid;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}
