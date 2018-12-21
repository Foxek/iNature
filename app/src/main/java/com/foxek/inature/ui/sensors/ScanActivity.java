package com.foxek.inature.ui.sensors;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.foxek.inature.data.database.model.Sensor;
import com.foxek.inature.ui.preview.PreviewActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.foxek.inature.common.Constants.DELIMITER;
import static com.foxek.inature.common.Constants.MY_CAMERA_REQUEST_CODE;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SensorActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestPermission();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat
                    .requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:
                if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    startActivity(new Intent(this, SensorActivity.class));
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        String[] result;
        Intent intent = new Intent(this, PreviewActivity.class);

        if (rawResult.toString().contains(DELIMITER)) {
            result = rawResult.toString().split(DELIMITER, 2);

            intent.putExtra("product_id", result[0]);
            intent.putExtra("mac_address", result[1]);
        }else {
            intent.putExtra("product_id", "null");
            intent.putExtra("mac_address", "null");
        }
        startActivity(intent);
        finish();
    }
}


