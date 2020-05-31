package com.example.employeetracker.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmpTrackResponse {

@SerializedName("logins")
@Expose
private List<Login> logins = null;
@SerializedName("image_path")
@Expose
private String imagePath;
@SerializedName("trackings")
@Expose
private List<Tracking> trackings = null;
@SerializedName("msg")
@Expose
private String msg;

public List<Login> getLogins() {
return logins;
}

public void setLogins(List<Login> logins) {
this.logins = logins;
}

public String getImagePath() {
return imagePath;
}

public void setImagePath(String imagePath) {
this.imagePath = imagePath;
}

public List<Tracking> getTrackings() {
return trackings;
}

public void setTrackings(List<Tracking> trackings) {
this.trackings = trackings;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

}