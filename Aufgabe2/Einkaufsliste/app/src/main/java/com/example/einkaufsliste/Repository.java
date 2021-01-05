package com.example.einkaufsliste;

import com.example.einkaufsliste.models.User;

public class Repository {

    public static final int INVALID_USER = 100;
    public static final int INVALID_PASSWORD = 101;
    public static final int LOGIN_SUCCEED = 102;
    public static final int REGISTER_SUCCEED = 103;
    public static final int USER_ALREADY_EXIST = 104;

    private static volatile Repository instance;

    private User user = null;

    private String ipAddress = "141.87.68.8";

    private Repository() {

    }

    public static Repository getInstance(){
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
