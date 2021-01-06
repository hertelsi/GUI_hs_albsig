package com.example.einkaufsliste.models;


import com.example.einkaufsliste.ui.lists.ListDataAdapter;

import java.util.ArrayList;

public class ListData {
    private static ArrayList<String> Liste;
    private ListDataAdapter adapter;
    private String text;
    private int menge;
    private int id;

    public ListData(int id, String text){
        this.id = id;
        this.text = text;
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


    public static void AddContent(String content){
        Liste.add(content);
    }




}
