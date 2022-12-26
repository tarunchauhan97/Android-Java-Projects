package com.example.androidjavaprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView tvDrinkName, tvLoginResult;
    ProgressBar progressBar;
    Button bGetDrink;

    MainViewModel mViewModel;
    EditText etEmail, etPassword;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDrinkName = findViewById(R.id.tvDrinkName);
        progressBar = findViewById(R.id.progressBar);
        bGetDrink = findViewById(R.id.bGetDrink);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        tvLoginResult = findViewById(R.id.tvLoginResult);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mViewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                progressBar.setVisibility(visibility);
            }
        });

        mViewModel.getDrink().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String drinkName) {
                tvDrinkName.setText(drinkName);
            }
        });

        mViewModel.suggestNewDrink();

        bGetDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.suggestNewDrink();
            }
        });
        mViewModel.getLoginResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvLoginResult.setText(s);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

}