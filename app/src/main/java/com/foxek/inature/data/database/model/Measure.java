package com.foxek.inature.data.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Sensor.class, parentColumns = "uid", childColumns = "sensorId", onDelete = CASCADE))
public class Measure {

    Measure(){

    }

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "value")
    private String value;

    @ColumnInfo(name = "icon")
    private String icon;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "sensorId")
    private int sensorId;

    public Measure(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String parseCayennePayload(int value, String name){
        switch (name) {
            case "Температура":
                return String.valueOf((float)(value/10.0)) + " \u2103";
            case "Влажность воздуха":
            case "Влажность почвы":
                return String.valueOf(value) + " %";
            case "Освещённость":
                return String.valueOf(value) + " L";
            default:
                return String.valueOf(value) + " ";
        }

    }
}