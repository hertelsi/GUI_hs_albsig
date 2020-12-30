package com.example.einkaufsliste.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.einkaufsliste.LoginRepository;
import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.google.android.material.navigation.NavigationView;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button btnLogin;
    private Button btnRegister;
    private TextView tvResult;
    private EditText etUser;
    private EditText etPassword;
    private TextView tvUsername;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        View main = inflater.inflate(R.layout.activity_main, container, false);
        NavigationView navView = main.findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        btnLogin = root.findViewById(R.id.login);
        btnRegister = root.findViewById(R.id.register);
        tvResult = root.findViewById(R.id.loginError);
        etUser = root.findViewById(R.id.username);
        etPassword = root.findViewById(R.id.password);
        tvUsername = header.findViewById(R.id.tvUsername);
        loginViewModel.getResultMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvResult.setText(s);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(etUser.getText().toString(), etPassword.getText().toString());
                if (LoginRepository.getInstance().getUser()!=null){
                    tvUsername.setText(LoginRepository.getInstance().getUser().getUsername());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.register(etUser.getText().toString(), etPassword.getText().toString());
            }
        });

        return root;
    }
}