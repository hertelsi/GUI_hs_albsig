package com.example.einkaufsliste.models;

import java.util.Date;

public class BuyingList {
    private int id;
    private String text;
    private Date date;

    public BuyingList(int id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public BuyingList(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public BuyingList() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BuyingList{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
