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

    private static final int RC_SIGN_IN = 1;
    private HomeViewModel HomeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

//        signInHomeViewModel.signOut();
        checkIfSignedIn();
    }


    /**
     * If user is not authenticated, send him to SignIn first.
     * Else send him to Home
     */
    private void checkIfSignedIn() {
        HomeViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                System.out.println("Not workin----------------->" + user.getEmail());
                startHomeFragment();
            } else {
                System.out.println("Cool  ----------------->" + user);
                startLoginActivity();
            }
        });
    }

    /**
     * If user is authenticated the main activity content view is changed to main
     */
    private void startHomeFragment() {
        startActivity(new Intent(this, HomeActivity.class));
        HomeViewModel.init();
        finish();
//            startActivity(new Intent(this, MainActivity.class));
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_google)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK) {
            startHomeFragment();
        } else
            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
    }


}