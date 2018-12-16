package com.foxek.inature.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sensor")
public class Sensor {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "sensor_name")
    private String name;

    @ColumnInfo(name = "sensor_type")
    private String type;

    @ColumnInfo(name = "sensor_mac")
    private String address;

    @ColumnInfo(name = "sensor_icon")
    private String icon;

    public Sensor(int uid, String name, String type, String address, String icon) {
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.address = address;
        this.icon = icon;
    }

    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
