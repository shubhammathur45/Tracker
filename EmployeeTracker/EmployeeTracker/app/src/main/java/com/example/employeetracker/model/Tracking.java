package com.example.employeetracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracking {

@SerializedName("id")
@Expose
private String id;
@SerializedName("emp_id")
@Expose
private String empId;
@SerializedName("location")
@Expose
private String location;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("tracking_time")
@Expose
private String trackingTime;

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

public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
}

public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}

public String getTrackingTime() {
return trackingTime;
}

public void setTrackingTime(String trackingTime) {
this.trackingTime = trackingTime;
}

}