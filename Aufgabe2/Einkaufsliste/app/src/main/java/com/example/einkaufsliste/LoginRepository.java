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

    public int login(String username, String password) {
        user = new User( "Tim","123", 1);
        Date d = SampleData.getDate(0);
        Shop s = new Shop(0,"test", "test");
        BuyingList b = new BuyingList(2,"test",d,d,null, s);
        Article a = new Article(0,"kg", 10, "Bananen", b);
        //service.addArticle(a);


        User u = new User(username,password);
        u = service.login(u);
        if (u!=null){
            user = u;
            return LOGIN_SUCCEED;
        }
        return INVALID_PASSWORD;
    }

    public int register(String username, String password){
        User u = new User(username,password);
        service.register(u);
        if (!username.matches("^[a-zA-Z0-9]*$") || username.equals(""))
            return INVALID_USER;
        if (password.length()<3)
            return INVALID_PASSWORD;
        if (!SampleData.getUserNames().contains(username)){
            SampleData.addUser(new User(username,password));
            return REGISTER_SUCCEED;
        } else
            return USER_ALREADY_EXIST;
    }

    public void logout() {
        user = null;
    }
}
