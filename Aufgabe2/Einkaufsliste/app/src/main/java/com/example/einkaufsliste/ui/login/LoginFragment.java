package com.example.einkaufsliste.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.einkaufsliste.ChangeFragmentInterface;
import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.R;
import com.google.android.material.navigation.NavigationView;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button btnLogin;
    private Button btnRegister;
    private TextView tvResult;
    private EditText etUser;
    private EditText etPassword;
    private ConstraintLayout layoutLogin;
    private ConstraintLayout layoutLogout;
    private TextView tvLogout;
    private Button btnLogout;
    private ChangeFragmentInterface changeFragmentInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = root.findViewById(R.id.login);
        btnRegister = root.findViewById(R.id.register);
        tvResult = root.findViewById(R.id.loginError);
        etUser = root.findViewById(R.id.username);
        etPassword = root.findViewById(R.id.password);
        layoutLogin = root.findViewById(R.id.layoutLogin);
        layoutLogout = root.findViewById(R.id.layoutLogout);
        tvLogout = root.findViewById(R.id.tvLogout);
        btnLogout = root.findViewById(R.id.logout);
        changeFragmentInterface = ((MainActivity)getActivity());


        if (Repository.getInstance().getUser() != null){
            tvLogout.setText(Repository.getInstance().getUser().getName());
            layoutLogin.setVisibility(View.INVISIBLE);
            layoutLogout.setVisibility(View.VISIBLE);
        }

        loginViewModel.getResultMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("login succeed")){
                    tvLogout.setText(Repository.getInstance().getUser().getName());
                    layoutLogin.setVisibility(View.INVISIBLE);
                    layoutLogout.setVisibility(View.VISIBLE);
                    changeFragmentInterface.setUsernameText(Repository.getInstance().getUser().getName());
                    changeFragmentInterface.setLoginItemText("Logout");
                }
                tvResult.setText(s);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(etUser.getText().toString(), etPassword.getText().toString());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.register(etUser.getText().toString(), etPassword.getText().toString());
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.logout();
                layoutLogout.setVisibility(View.INVISIBLE);
                layoutLogin.setVisibility(View.VISIBLE);
                tvResult.setText("");
                changeFragmentInterface.setLoginItemText("Login");
                changeFragmentInterface.setUsernameText("Username");
            }
        });

        return root;
    }
}