package com.teamdui.profiler.ui.camera;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CameraViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Camera fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}