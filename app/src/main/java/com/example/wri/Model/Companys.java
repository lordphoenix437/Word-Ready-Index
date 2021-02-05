package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Companys implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameCompany")
    @Expose
    private String nameCompany;
    @SerializedName("thumbnailCompany")
    @Expose
    private Object thumbnailCompany;
    @SerializedName("addressCompany")
    @Expose
    private Object addressCompany;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("valuePacket")
    @Expose
    private String valuePacket;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;



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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Object getThumbnailCompany() {
        return thumbnailCompany;
    }

    public void setThumbnailCompany(Object thumbnailCompany) {
        this.thumbnailCompany = thumbnailCompany;
    }

    public Object getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(Object addressCompany) {
        this.addressCompany = addressCompany;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getValuePacket() {
        return valuePacket;
    }

    public void setValuePacket(String valuePacket) {
        this.valuePacket = valuePacket;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

