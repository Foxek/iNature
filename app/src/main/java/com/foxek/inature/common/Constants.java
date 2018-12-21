package com.foxek.inature.common;

import android.os.ParcelUuid;

public class Constants {
    public final static int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public final static int MY_CAMERA_REQUEST_CODE = 100;
    public final static int REQUEST_ENABLE_BT = 10;
    public final static String DATABASE_URL =  "https://smartsensor-e29f6.firebaseio.com/";
    public final static String MAC_PATTERN = "([\\da-fA-F]{2}(?:\\:|-|$)){6}";
    public final static String DELIMITER = "!#!";

    public final static ParcelUuid SENSOR_DATA_UUID = ParcelUuid.fromString("0000181c-0000-1000-8000-00805f9b34fb");

    public static final String EXTRA_ARGS = "args";



    public static final String CREATE_STATE = "create_state";
    public static final boolean SUCCESS = true;
    public static final boolean ERROR = false;
}
