package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Teacher implements Serializable {
    @SerializedName("codeTeacher")
    @Expose
    private String codeTeacher;
    @SerializedName("nameTeacher")
    @Expose
    private String nameTeacher;
    @SerializedName("descriptionTeacher")
    @Expose
    private String descriptionTeacher;
    @SerializedName("thumbnailTeacher")
    @Expose
    private String thumbnailTeacher;
    @SerializedName("emailUser")
    @Expose
    private String emailUser;
    @SerializedName("phoneUser")
    @Expose
    private String phoneUser;
    @SerializedName("idClass")
    @Expose
    private String idClass;
    @SerializedName("isSuccess")
    @Expose
    private Integer isSuccess;
    private boolean selected = false;
    public Teacher(){

    }
    public Teacher(String nameTeacher,String codeTeacher, boolean selected) {
        this.nameTeacher = nameTeacher;
        this.codeTeacher = codeTeacher;
        this.selected = selected;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getCodeTeacher() {
        return codeTeacher;
    }

    public void setCodeTeacher(String codeTeacher) {
        this.codeTeacher = codeTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getDescriptionTeacher() {
        return descriptionTeacher;
    }

    public void setDescriptionTeacher(String descriptionTeacher) {
        this.descriptionTeacher = descriptionTeacher;
    }

    public String getThumbnailTeacher() {
        return thumbnailTeacher;
    }

    public void setThumbnailTeacher(String thumbnailTeacher) {
        this.thumbnailTeacher = thumbnailTeacher;
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