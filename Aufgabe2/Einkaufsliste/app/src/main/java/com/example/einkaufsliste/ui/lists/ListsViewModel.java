package com.example.einkaufsliste.ui.lists;

import android.widget.DatePicker;
import android.widget.EditText;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.InfrastructureWebservice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListsViewModel extends ViewModel {

    private InfrastructureWebservice service;
    private MutableLiveData<Collection<BuyingList>> allbuyingLists = new MutableLiveData<Collection<BuyingList>>();

    public ListsViewModel() {
        super();
        service = new InfrastructureWebservice();
        User user = Repository.getInstance().getUser();
        if (user != null) {
            allbuyingLists.setValue(Repository.getInstance().getUser().getBuyingLists());
        }
    }

    public LiveData<Collection<BuyingList>> getAllbuyingLists() {
        return allbuyingLists;
    }

    public void setAllbuyingLists(Collection<BuyingList> allbuyingLists){
        this.allbuyingLists.postValue(allbuyingLists);
    }

    public void removeBuyingList(int position){
        Collection<BuyingList> buyingLists = allbuyingLists.getValue();
        BuyingList buyingList = (BuyingList) buyingLists.toArray()[position];
        Repository.getInstance().setRunPollingThread(false);
        service.deleteBuyingList(buyingList);
        Repository.getInstance().setRunPollingThread(true);
    }

    public void addOneBuyingList(EditText buyingListName, DatePicker datePicker){
        String name = buyingListName.getText().toString();
        Date date = getDateFromDatePicker(datePicker);
        User user = Repository.getInstance().getUser();
        if ((name != null) && (date != null)){
            BuyingList newBuyingList = new BuyingList(name, date);
            //user.getBuyingLists().add(newBuyingList);
            Repository.getInstance().setRunPollingThread(false);
            service.addBuyingList(newBuyingList);
            Repository.getInstance().setRunPollingThread(true);
        }
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return new Date(calendar.getTimeInMillis());
    }
}