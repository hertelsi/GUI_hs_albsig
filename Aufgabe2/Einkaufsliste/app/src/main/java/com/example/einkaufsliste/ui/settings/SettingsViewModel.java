package com.example.einkaufsliste.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.Repository;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(Repository.getInstance().getIpAddress());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setIp(String ip){
        Repository.getInstance().setIpAddress(ip);
    }
}