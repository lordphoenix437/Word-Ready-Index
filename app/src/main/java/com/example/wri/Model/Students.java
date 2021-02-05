package com.example.wri.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Students implements Serializable {
    @SerializedName("isSuccess")
    @Expose
    private Integer isSuccess;
    @SerializedName("message")
    @Expose
    private String message;
//
@SerializedName("id")
@Expose
private String id;
    @SerializedName("codeStudent")
    @Expose
    private String codeStudent;
    @SerializedName("nameStudent")
    @Expose
    private String nameStudent;
    @SerializedName("thumbnailStudent")
    @Expose
    private String thumbnailStudent;
    @SerializedName("birthdayStudent")
    @Expose
    private String birthdayStudent;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("phoneUser")
    @Expose
    private String phoneUser;
    @SerializedName("idClass")
    @Expose
    private String idClass;

    @SerializedName("idgmajor")
    @Expose
    private String idgmajor;


    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    private boolean selected = false;

    public Students(String nameStudent,String codeStudent, boolean selected) {
        this.nameStudent = nameStudent;
        this.codeStudent = codeStudent;
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Students() {
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

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getThumbnailStudent() {
        return thumbnailStudent;
    }

    public void setThumbnailStudent(String thumbnailStudent) {
        this.thumbnailStudent = thumbnailStudent;
    }

    public String getBirthdayStudent() {
        return birthdayStudent;
    }

    public void setBirthdayStudent(String birthdayStudent) {
        this.birthdayStudent = birthdayStudent;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }



}
