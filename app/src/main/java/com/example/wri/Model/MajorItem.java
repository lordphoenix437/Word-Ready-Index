package com.example.wri.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MajorItem implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("namegmajor")
    @Expose
    private String namegmajor;
    @SerializedName("sumgmajor")
    @Expose
    private Object sumgmajor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamegmajor() {
        return namegmajor;
    }

    public void setNamegmajor(String namegmajor) {
        this.namegmajor = namegmajor;
    }

    public Object getSumgmajor() {
        return sumgmajor;
    }

    public void setSumgmajor(Object sumgmajor) {
        this.sumgmajor = sumgmajor;
    }

}
