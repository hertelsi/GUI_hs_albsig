package com.example.einkaufsliste.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> resultMsg = new MutableLiveData<>();

    User testUser = new User("test","test");

    public LoginViewModel() {
    }

    public LiveData<String> getResultMsg() {
        return resultMsg;
    }

    public void login(String username, String password){
        if (username.equals(testUser.getUsername()) && password.equals(testUser.getPassword())){
            resultMsg.setValue("password correct");
        }
        else {
            resultMsg.setValue("wrong password");
        }
    }
}