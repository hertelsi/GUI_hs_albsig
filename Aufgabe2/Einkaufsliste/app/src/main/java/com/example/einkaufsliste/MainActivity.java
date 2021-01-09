package com.example.einkaufsliste;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.User;
import com.example.einkaufsliste.rest.InfrastructureWebservice;
import com.example.einkaufsliste.ui.lists.ListDataFragment;
import com.example.einkaufsliste.ui.lists.ListDataViewModel;
import com.example.einkaufsliste.ui.lists.ListsViewModel;
import com.example.einkaufsliste.ui.login.LoginFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Collection;
import java.util.Observable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements ChangeFragmentInterface{

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvUsername;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_lists, R.id.nav_login, R.id.nav_settings, R.id.nav_singleList)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        startThreadForUpdatingTheUser();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void changeFragment(int id) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(id);
    }

    private void startThreadForUpdatingTheUser() {
        ListsViewModel listsViewModel = new ViewModelProvider(this).get(ListsViewModel.class);
        ListDataViewModel listDataViewModel = new ViewModelProvider(this).get(ListDataViewModel.class);
        Thread pollingThreadForUpdates = new Thread() {
            InfrastructureWebservice service = new InfrastructureWebservice();

            public void run() {
                while (true) {
                    if (Repository.getInstance().getRunPollingThread() == true) {
                        try {
                            User currentUser = Repository.getInstance().getUser();
                            if (currentUser != null) {

                                User user = service.login(currentUser);
                                Repository.getInstance().setUser(user);
                                listsViewModel.setAllbuyingLists(user.getBuyingLists());
                                BuyingList b = user.getBuyingListById(Repository.getInstance().getCurrentBuyingListId());
                                listDataViewModel.setBuyingList(b);
                                listDataViewModel.setAllArticles(b.getAllArticles());
                            }
                            sleep(150);
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
            }
        };
        pollingThreadForUpdates.start();
    }

    @Override
    public void setUsernameText(String name){
        View headerView = navigationView.getHeaderView(0);
        tvUsername = headerView.findViewById(R.id.tvUsername);
        tvUsername.setText(name);
    }

    @Override
    public void setLoginItemText(String name){
        Menu menu = navigationView.getMenu();
        MenuItem nav_login = menu.findItem(R.id.nav_login);
        nav_login.setTitle(name);
    }
}