package com.example.einkaufsliste.ui.lists;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LoginViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }
}