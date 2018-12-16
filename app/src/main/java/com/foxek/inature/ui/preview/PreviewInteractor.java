package com.foxek.inature.ui.preview;

import com.foxek.inature.data.model.SensorRepository;
import com.foxek.inature.data.network.ApiHelper;
import com.foxek.inature.di.NetworkHelper;

import javax.inject.Inject;

public class PreviewInteractor implements PreviewMvpInteractor {

    @Inject
    public PreviewInteractor(@NetworkHelper ApiHelper apiHelper, SensorRepository sensorRepository){

    }
}
