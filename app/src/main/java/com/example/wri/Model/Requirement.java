package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Requirement {
    @SerializedName("isSuccess")
    @Expose
    private Integer isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameReq")
    @Expose
    private String nameReq;
    @SerializedName("nameGroupReq")
    @Expose
    private String nameGroupReq;
    private boolean selected = false;
    public Requirement(String nameReq,String nameGroupReq, boolean selected) {
        this.nameReq = nameReq;
        this.nameGroupReq = nameGroupReq;
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameReq() {
        return nameReq;
    }

    public void setNameReq(String nameReq) {
        this.nameReq = nameReq;
    }

    public String getNameGroupReq() {
        return nameGroupReq;
    }

    public void setNameGroupReq(String nameGroupReq) {
        this.nameGroupReq = nameGroupReq;
    }
}
