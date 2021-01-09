package com.example.einkaufsliste.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.InfrastructureWebservice;
import com.example.einkaufsliste.ui.lists.ListsViewModel;

import java.net.SocketTimeoutException;

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
        try {
            u = service.login(u);
        } catch (SocketTimeoutException e) {
            resultMsg.setValue("Timeout: Change IP-Address");
            return;
        }
        if (u == null)
            resultMsg.setValue("password not correct");
        else {
            Repository.getInstance().setUser(u);
            //startThreadForUpdatingTheUser();
            resultMsg.setValue("login succeed");
        }
    }

    public void register(String username, String password){
        InfrastructureWebservice service = new InfrastructureWebservice();
        User u = new User(username,password);
        try {
            u = service.register(u);
        } catch (SocketTimeoutException e) {
            resultMsg.setValue("Timeout: Change IP-Address");
            return;
        }
        if (u == null)
            resultMsg.setValue("User already exist");
        else
            resultMsg.setValue("register succeed");
    }

    public void logout(){
        Repository.getInstance().setUser(null);
    }
}