package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student_Teacher_Class_Point {
    @SerializedName("isSuccess")
    @Expose
    private Integer isSuccess;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
