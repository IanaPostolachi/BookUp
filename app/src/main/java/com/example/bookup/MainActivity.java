package com.example.bookup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookup.ui.home.HomeViewModel;
import com.firebase.ui.auth.AuthUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HomeViewModel HomeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        checkIfSignedIn();
    }

    private void checkIfSignedIn() {
        HomeViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                startHomeFragment();
            } else {
                startLoginActivity();
            }
        });
    }

    private void startHomeFragment() {
        startActivity(new Intent(this, HomeActivity.class));
        HomeViewModel.init();
        finish();
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }
}