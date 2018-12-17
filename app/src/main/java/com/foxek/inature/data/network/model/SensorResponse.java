package com.foxek.inature.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SensorResponse {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("type")
    @Expose
    private String type;

    @Expose
    @SerializedName("Measure")
    private List<MeasureMeta> measureMetaInfo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MeasureMeta> getMeasureMetaInfo() {
        return measureMetaInfo;
    }

    public void setMeasureMetaInfo(List<MeasureMeta> measureMetaInfo) {
        this.measureMetaInfo = measureMetaInfo;
    }

}
