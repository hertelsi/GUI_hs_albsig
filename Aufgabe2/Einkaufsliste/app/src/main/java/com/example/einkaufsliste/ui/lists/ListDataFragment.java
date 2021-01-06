package com.example.einkaufsliste.ui.lists;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListDataFragment extends Fragment {
    private FloatingActionButton fab1;
    private ArrayList<String> content;
    private ListDataAdapter adapter;
    private EditText edit_text_ListData;
    private ListDataViewModel listDataViewModel;
    private RecyclerView recyclerView;
    private Button btnAddUser;
    private TextView tvAddUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listDataViewModel = new ViewModelProvider(this).get(ListDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listdata, container, false);

        recyclerView = root.findViewById(R.id.rec_view_first);
        initRecyclerView(root);

        adapter = new ListDataAdapter(content);

        fab1 = root.findViewById(R.id.fabListData);
        edit_text_ListData = root.findViewById(R.id.edit_text_ListData);
        btnAddUser = root.findViewById(R.id.btnAddUser);
        tvAddUser = root.findViewById(R.id.tvAddUser);
        // fab fuegt Editier-Inhalt der Liste hinzu
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                listDataViewModel.getListData().add(edit_text_ListData.getText().toString());
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
    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListDataAdapter(listDataViewModel.getListData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
