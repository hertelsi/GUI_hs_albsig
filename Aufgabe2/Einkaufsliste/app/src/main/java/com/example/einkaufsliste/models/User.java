package com.example.einkaufsliste.models;

import java.io.Serializable;
import java.util.Collection;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private long id;
    private Collection<BuyingList> buyingLists;

    public User( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public Collection<BuyingList> getBuyingLists() {
        return buyingLists;
    }

    public void setBuyingLists(Collection<BuyingList> buyingLists) {
        this.buyingLists = buyingLists;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "User[" +
                ", username='" + username +
                ']';
    }
}
