package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Points implements Serializable {
    @SerializedName("idStu")
    @Expose
    private String idStu;
    @SerializedName("idReq")
    @Expose
    private String idReq;
    @SerializedName("idClass")
    @Expose
    private String idClass;
    @SerializedName("nameReq")
    @Expose
    private String nameReq;
    @SerializedName("nameGroupReq")
    @Expose
    private String nameGroupReq;
    @SerializedName("point")
    @Expose
    private String point;
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

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getIdReq() {
        return idReq;
    }

    public void setIdReq(String idReq) {
        this.idReq = idReq;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }


}
