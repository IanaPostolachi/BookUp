package com.example.bookup.Repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.bookup.Model.DAOs.UserDAO;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;

public class UsersRepository {
    private final UserDAO currentUser;
    private final Application app;
    private static UsersRepository instance;

    private UsersRepository(Application app) {
        this.app = app;
        currentUser = new UserDAO();
    }

    public static synchronized UsersRepository getInstance(Application app) {
        if (instance == null)
            instance = new UsersRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
}
