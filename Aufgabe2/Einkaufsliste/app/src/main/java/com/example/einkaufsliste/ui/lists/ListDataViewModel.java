package com.example.einkaufsliste.ui.lists;

import android.widget.DatePicker;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.Article;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.ListData;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.InfrastructureWebservice;
import com.example.einkaufsliste.ui.sampleData.SampleData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListDataViewModel extends ViewModel {

    private InfrastructureWebservice service;
    private MutableLiveData<Collection<Article>> allArticles = new MutableLiveData<Collection<Article>>();
    private BuyingList buyingList;

    public ListDataViewModel(){
        super();
        service = new InfrastructureWebservice();
        User user = Repository.getInstance().getUser();
        if (user != null) {
            user.getBuyingListById(Repository.getInstance().getCurrentBuyingListId());
            allArticles.setValue(buyingList.getAllArticles());
        }

    }
    public LiveData<Collection<Article>> getAllArticles() { return allArticles;}

    public void setAllArticles(Collection<Article> articles){
        this.allArticles.postValue(articles);
    }

    public int addUser(String username){
        InfrastructureWebservice service = new InfrastructureWebservice();
        return service.addUserToBuyingList(Repository.getInstance().getCurrentBuyingListId(), username);
    }

    public void removeArticle(int position){
        Collection<Article> articles = allArticles.getValue();
        Article article = (Article) articles.toArray()[position];
        Repository.getInstance().setRunPollingThread(false);
        service.deleteArticle(article);
        Repository.getInstance().setRunPollingThread(true);
    }

    public void addOneArticle(String unit, long amount, String name){
        if ((unit != null) && (amount != 0) && (name != null)){
            Article newArticle = new Article(unit, amount, name, buyingList);
            Repository.getInstance().setRunPollingThread(false);
            service.addArticle(newArticle);
            Repository.getInstance().setRunPollingThread(true);
        }
    }
}
