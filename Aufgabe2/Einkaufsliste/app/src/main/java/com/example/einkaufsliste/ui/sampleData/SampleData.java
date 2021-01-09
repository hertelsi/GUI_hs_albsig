package com.example.einkaufsliste.ui.sampleData;

import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    private static final String BUYINGLIST_1 = "Wocheneinkauf im Supermarkt";
    private static final String BUYINGLIST_2 = "Baumarkteinkauf";
    private static final String BUYINGLIST_3 = "Ikeaeinkauf";
    private static final String BUYINGLIST_4 = "Einkauf auf dem Markt";
    private static final String BUYINGLIST_5 = "Klamottenshoppingtour";
    private static final String BUYINGLIST_6 = "Wocheneinkauf im Supermarkt";
    private static final String BUYINGLIST_7 = "Baumarkteinkauf";
    private static final String BUYINGLIST_8 = "Ikeaeinkauf";
    private static final String BUYINGLIST_9 = "Einkauf auf dem Markt";
    private static final String BUYINGLIST_10 = "Klamottenshoppingtour";

    private static final String ListData_1 = "Banane";
    private static final String ListData_2 = "Apfel";

    private static List<User> user = new ArrayList<>();

    public static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return new Date(cal.getTimeInMillis());
    }
    /*
    public static List<BuyingList> getBuyingLists() {
        List<BuyingList> buyingLists = new ArrayList<>();
        buyingLists.add(new BuyingList(1, BUYINGLIST_1,  getDate(0)));
        buyingLists.add(new BuyingList(2, BUYINGLIST_2,  getDate(-1)));
        buyingLists.add(new BuyingList(3, BUYINGLIST_3,  getDate(-2)));
        buyingLists.add(new BuyingList(4, BUYINGLIST_4,  getDate(-3)));
        buyingLists.add(new BuyingList(5, BUYINGLIST_5,  getDate(-4)));
        buyingLists.add(new BuyingList(6, BUYINGLIST_6,  getDate(-5)));
        buyingLists.add(new BuyingList(7, BUYINGLIST_7,  getDate(-6)));
        buyingLists.add(new BuyingList(8, BUYINGLIST_8,  getDate(-7)));
        buyingLists.add(new BuyingList(9, BUYINGLIST_9,  getDate(-8)));
        buyingLists.add(new BuyingList(10, BUYINGLIST_10,  getDate(-9)));
        return buyingLists;
    }
    */
    public static List<User> getUser(){
        return user;
    }

    public static void addUser(User u){
        user.add(u);
    }

    public static List<String> getUserNames(){
        List<String> result = new ArrayList<>();
        for (User user : getUser()){
            result.add(user.getName());
        }
        return result;
    }

    public static ArrayList<String> getListData(){
        ArrayList<String> DataList = new ArrayList<>();
        DataList.add(ListData_1);
        DataList.add(ListData_2);
        return DataList;
    }
}
