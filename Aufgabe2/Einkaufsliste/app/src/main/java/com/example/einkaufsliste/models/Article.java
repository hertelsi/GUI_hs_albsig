package com.example.einkaufsliste.models;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String unit;
    private long amount;
    private String name;
    private BuyingList buyingList;

    public Article(long id, String unit, long amount, String name, BuyingList buyingList) {
        this.id = id;
        this.unit = unit;
        this.amount = amount;
        this.name = name;
        this.buyingList = buyingList;
    }

    public Article(String unit, long amount, String name, BuyingList buyingList) {
        this.unit = unit;
        this.amount = amount;
        this.name = name;
        this.buyingList = buyingList;
    }

    public BuyingList getBuyingList() {
        return buyingList;
    }

    public void setBuyingList(BuyingList buyingList) {
        this.buyingList = buyingList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
