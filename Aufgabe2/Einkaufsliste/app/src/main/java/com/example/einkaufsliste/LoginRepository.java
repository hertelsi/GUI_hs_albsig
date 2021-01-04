package com.example.einkaufsliste;

import com.example.einkaufsliste.models.Article;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.Shop;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.IllegalCreateException;
import com.example.einkaufsliste.rest.InfrastructureWebservice;
import com.example.einkaufsliste.rest.NoSuchRowException;
import com.example.einkaufsliste.ui.sampleData.SampleData;

import java.util.Date;

public class LoginRepository {

    public static final int INVALID_USER = 100;
    public static final int INVALID_PASSWORD = 101;
    public static final int LOGIN_SUCCEED = 102;
    public static final int REGISTER_SUCCEED = 103;
    public static final int USER_ALREADY_EXIST = 104;

    private static volatile LoginRepository instance;

    private User user = null;

    private final InfrastructureWebservice service;

    private LoginRepository() {
        service = new InfrastructureWebservice();
    }

    public static LoginRepository getInstance(){
        if (instance == null){
            instance = new LoginRepository();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
