package com.example.einkaufsliste.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class BuyingList  implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Date creationDate;
    private Date buyingDate;
    private Collection<Article> allArticles;
    private Shop shop;

    public BuyingList(int id, String name, Date creationDate, Date buyingDate, Collection<Article> allArticles, Shop shop) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.buyingDate = buyingDate;
        this.allArticles = allArticles;
        this.shop = shop;
    }

    public BuyingList(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.creationDate = date;
    }

    public BuyingList(String name, Date date) {
        this.name = name;
        this.creationDate = date;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

    public Collection<Article> getAllArticles() {
        return allArticles;
    }

    public void setAllArticles(Collection<Article> allArticles) {
        this.allArticles = allArticles;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }



    public BuyingList() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return creationDate;
    }

    public void setDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public String toString() {
        return "BuyingList{" +
                "id=" + id +
                ", text='" + name + '\'' +
                ", date=" + creationDate +
                '}';
    }
}
