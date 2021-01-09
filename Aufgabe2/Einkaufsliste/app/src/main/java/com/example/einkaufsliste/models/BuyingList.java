package com.example.einkaufsliste.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;
import java.util.GregorianCalendar;

public class BuyingList  implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Date creationDate;
    private Date buyingDate;
    private Collection<Article> allArticles;

    public BuyingList(int id, String name, Date creationDate, Date buyingDate, Collection<Article> allArticles) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.buyingDate = buyingDate;
        this.allArticles = allArticles;
    }

    public BuyingList(int id, String name, Date buyingDate, Date creationDate) {
        this.id = id;
        this.name = name;
        this.buyingDate = buyingDate;
    }

    public BuyingList(String name, Date buyingDate, Date creationDate) {
        this.name = name;
        this.buyingDate = buyingDate;
        this.creationDate = creationDate;
    }

    public BuyingList(String name, Date buyingDate) {
        this.name = name;
        this.buyingDate = buyingDate;
        this.creationDate = getCurrentDate();
    }

    private Date getCurrentDate(){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
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
