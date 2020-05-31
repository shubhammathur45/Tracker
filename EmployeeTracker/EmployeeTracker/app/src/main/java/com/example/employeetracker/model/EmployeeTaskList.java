package com.example.employeetracker.model;

/**
 * Created by aayu on 2/4/2017.
 */



    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    public class EmployeeTaskList {

        @SerializedName("id")
        @Expose
        private String id;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

    }

