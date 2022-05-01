package com.example.bookup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private GoogleSignInClient GoogleSignInClient;
    private static final int RC_SIGN_IN = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button button = findViewById(R.id.sign_in_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , MainActivity.class);
                startActivity(intent);
            }
        });

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        GoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        Button button = findViewById(R.id.sign_in_btn);
//        button.setOnClickListener(v -> signIn());
//
//        Button buttonMail = findViewById(R.id.sign_in_Email);
//        buttonMail.setOnClickListener(v -> signInWithMail());
//
//    }
//
//    private void signIn() {
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build());
//
//        Intent signInIntent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build();
//
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//    private void signInWithMail() {
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build());
//
//        Intent signInIntent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .setLogo(R.drawable.ic_google)
//                .build();
//
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            handleSignInRequest(resultCode);
//        }
//    }
//
//    private void handleSignInRequest(int resultCode) {
//        if (resultCode == RESULT_OK) {
//            startMainActivity();
//        } else
//            Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
//    }
//
//    private void startMainActivity() {
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }
}