package com.example.einkaufsliste.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.InfrastructureWebservice;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> resultMsg = new MutableLiveData<>();


    public LoginViewModel() {
    }

    public LiveData<String> getResultMsg() {
        return resultMsg;
    }

    public void login(String username, String password) {
        InfrastructureWebservice service = new InfrastructureWebservice();
        User u = new User(username,password);
        u = service.login(u);
        if (u == null)
            resultMsg.setValue("password not correct");
        else {
            Repository.getInstance().setUser(u);
            resultMsg.setValue("login succeed");
        }
    }

    public void register(String username, String password){
        InfrastructureWebservice service = new InfrastructureWebservice();
        User u = new User(username,password);
        u = service.register(u);
        if (u == null)
            resultMsg.setValue("User already exist");
        else
            resultMsg.setValue("register succeed");
    }

    public void logout(){
        Repository.getInstance().setUser(null);
    }
}