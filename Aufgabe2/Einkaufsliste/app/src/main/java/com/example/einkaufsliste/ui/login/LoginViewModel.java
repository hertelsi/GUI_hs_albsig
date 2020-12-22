package com.example.einkaufsliste.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.LoginRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> resultMsg = new MutableLiveData<>();


    public LoginViewModel() {
    }

    public LiveData<String> getResultMsg() {
        return resultMsg;
    }

    public void login(String username, String password){
        int result = LoginRepository.getInstance().login(username,password);
        if (result==LoginRepository.LOGIN_SUCCEED){
            resultMsg.setValue("password correct");
        }
        else if (result==LoginRepository.INVALID_PASSWORD) {
            resultMsg.setValue("password not correct");
        }else {
            resultMsg.setValue("invalid user");
        }
    }

    public void register(String username, String password){
        int result = LoginRepository.getInstance().register(username,password);
        if (result==LoginRepository.REGISTER_SUCCEED){
            resultMsg.setValue("register succeed");
        }
        else {
            resultMsg.setValue("User already exist");
        }
    }
}