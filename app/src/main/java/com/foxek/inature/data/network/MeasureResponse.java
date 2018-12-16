package com.foxek.inature.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeasureResponse {

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("name")
    @Expose
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
