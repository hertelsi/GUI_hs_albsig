package com.example.einkaufsliste.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.einkaufsliste.R;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button btnLogin;
    private TextView tvResult;
    private TextView tvUser;
    private TextView tvPassword;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = root.findViewById(R.id.login);
        tvResult = root.findViewById(R.id.loginError);
        tvUser = root.findViewById(R.id.username);
        tvPassword = root.findViewById(R.id.password);
        loginViewModel.getResultMsg().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvResult.setText(s);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login(tvUser.getText().toString(),tvPassword.getText().toString());
            }
        });


        return root;
    }
}