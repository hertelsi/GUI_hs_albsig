package com.example.einkaufsliste;

import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.ui.sampleData.SampleData;

public class LoginRepository {

    public static final int INVALID_USER = 100;
    public static final int INVALID_PASSWORD = 101;
    public static final int LOGIN_SUCCEED = 102;
    public static final int REGISTER_SUCCEED = 103;
    public static final int USER_ALREADY_EXIST = 104;

    private static volatile LoginRepository instance;

    private User user = null;

    private LoginRepository() {
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

    public int login(String username, String password){
        for (User user : SampleData.getUser()){
            if (user.getUsername().equals(username)){
                if (user.getPassword().equals(password)){
                    this.user = user;
                    return LOGIN_SUCCEED;
                }
                return INVALID_PASSWORD;
            }
        }
        return INVALID_USER;
    }

    public int register(String username, String password){
        if (!SampleData.getUserNames().contains(username)){
            SampleData.addUser(new User(1,username,password));
            return REGISTER_SUCCEED;
        } else {
            return USER_ALREADY_EXIST;
        }
    }

    public void logout() {
        user = null;
    }
}
