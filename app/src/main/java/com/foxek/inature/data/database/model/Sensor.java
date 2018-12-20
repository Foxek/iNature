package com.foxek.inature.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sensor", indices = @Index(value = {"sensor_name"}, unique = true))
public class Sensor {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "sensor_name")
    private String name;

    @ColumnInfo(name = "sensor_description")
    private String description;

    @ColumnInfo(name = "sensor_mac")
    private String address;

    @ColumnInfo(name = "sensor_icon")
    private String icon;

    @ColumnInfo(name = "sensor_type")
    private String type;

    public Sensor(int uid, String name, String description, String address, String icon) {
        this.uid = uid;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
