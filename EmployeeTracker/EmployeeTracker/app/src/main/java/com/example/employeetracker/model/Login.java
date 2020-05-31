package com.example.employeetracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

@SerializedName("id")
@Expose
private String id;
@SerializedName("emp_id")
@Expose
private String empId;
@SerializedName("location")
@Expose
private String location;
@SerializedName("image")
@Expose
private String image;
@SerializedName("login_time")
@Expose
private String loginTime;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getEmpId() {
return empId;
}

public void setEmpId(String empId) {
this.empId = empId;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getLoginTime() {
return loginTime;
}

public void setLoginTime(String loginTime) {
this.loginTime = loginTime;
}

}