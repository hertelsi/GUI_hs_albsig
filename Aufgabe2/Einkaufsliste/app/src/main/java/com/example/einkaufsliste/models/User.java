package com.example.einkaufsliste.models;

import java.io.Serializable;
import java.util.Collection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private long id;
    private Collection<BuyingList> allBuyingLists;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, long id) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public Collection<BuyingList> getBuyingLists() {
        return allBuyingLists;
    }

    public void setBuyingLists(Collection<BuyingList> buyingLists) {
        this.allBuyingLists = buyingLists;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", buyingLists=" + allBuyingLists +
                '}';
    }
}
