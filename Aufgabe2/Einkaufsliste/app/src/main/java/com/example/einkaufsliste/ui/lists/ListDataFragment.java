package com.example.einkaufsliste.ui.lists;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.example.einkaufsliste.models.Article;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.ListData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListDataFragment extends Fragment {
    private FloatingActionButton fab1;
    private List<ListData> content;
    private ListDataAdapter adapter;
    private EditText edit_text_ListData;
    private ListDataViewModel listDataViewModel;
    private RecyclerView recyclerView;
    private Button btnAddUser;
    private TextView tvAddUser;
    private Spinner spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listDataViewModel = new ViewModelProvider(getActivity()).get(ListDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listdata, container, false);

        recyclerView = root.findViewById(R.id.rec_view_first);
        initRecyclerView(root);

        fab1 = root.findViewById(R.id.fabListData);
        btnAddUser = root.findViewById(R.id.btnAddUser);
        tvAddUser = root.findViewById(R.id.tvAddUser);
        // fab fuegt Editier-Inhalt der Liste hinzu
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflatelistDataListDialog(view);
                adapter.notifyDataSetChanged();// Model benachrichtigen
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                View viewInflated = LayoutInflater.from(root.getContext()).inflate(R.layout.add_user_dialog, (ViewGroup) getView(),false);
                builder.setView(viewInflated);
                builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etUsername = viewInflated.findViewById(R.id.etAddUser);
                        String name = etUsername.getText().toString();
                        if (listDataViewModel.addUser(name) == -1)
                            tvAddUser.setText(R.string.userNotExist);
                        else
                            tvAddUser.setText(R.string.addedUser);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        return root;
    }
    private void inflatelistDataListDialog(@NonNull View root) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(root.getContext());
        //builder.setTitle(R.string.buyinglist_creation);

        View viewInflated = LayoutInflater.from(root.getContext()).inflate(R.layout.add_listdata_dialog, (ViewGroup) getView(), false);
        builder.setView(viewInflated);

        spinner =viewInflated.findViewById(R.id.spinner);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this.getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                Repository.getInstance().getUnitList());
        spinner.setAdapter(spinnerArrayAdapter);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    EditText listDataName = viewInflated.findViewById(R.id.listData_name);
                    EditText listDataAmount = viewInflated.findViewById(R.id.listData_amount);
                    String listDataUnit = (String) spinner.getSelectedItem();

                    String name = listDataName.getText().toString();
                    try {
                        int amount = Integer.parseInt(listDataAmount.getText().toString());
                        Log.d("test", listDataAmount.getText().toString());
                        if (name != null){
                            listDataViewModel.addOneArticle(listDataUnit, amount, name);
                            adapter.notifyDataSetChanged();
                            dialog.cancel();
                        }
                    }
                    catch ( Exception e ){

                    }

                } catch (Exception e){
                    Log.e("criticalErrorInAddBuyingListFunctionality", e.toString());
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListDataAdapter(((MainActivity)getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listDataViewModel.getAllArticles().observe(getViewLifecycleOwner(), new Observer<Collection<Article>>() {
            @Override
            public void onChanged(Collection<Article> articles) {
                adapter.setArticles(articles);
            }
        });
    }
}
