package com.example.einkaufsliste;

import com.example.einkaufsliste.models.User;

import java.util.ArrayList;

public class Repository {

    public static final int INVALID_USER = 100;
    public static final int INVALID_PASSWORD = 101;
    public static final int LOGIN_SUCCEED = 102;
    public static final int REGISTER_SUCCEED = 103;
    public static final int USER_ALREADY_EXIST = 104;

    private static volatile Repository instance;

    private User user = null;

    private String ipAddress = "141.87.68.230";

    private Boolean runPollingThread = true;

    private int currentBuyingListId;

    public int getCurrentBuyingListId() {
        return currentBuyingListId;
    }

    public void setCurrentBuyingListId(int currentBuyingListId) {
        this.currentBuyingListId = currentBuyingListId;
    }

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

    public Boolean getRunPollingThread() {
        return runPollingThread;
    }

    public void setRunPollingThread(Boolean runPollingThread) {
        this.runPollingThread = runPollingThread;
    }

    public ArrayList<String> getUnitList(){
        ArrayList<String> result = new ArrayList<>();
        result.add("Kg");
        result.add("Liter");
        result.add("Stück");
        result.add("Kasten");
        result.add("Flaschen");
        result.add("g");
        return result;

    }
}
