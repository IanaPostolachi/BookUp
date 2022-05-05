package com.example.bookup.ui.settings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookup.Repos.UsersRepository;
import com.google.firebase.auth.FirebaseUser;

public class SettingsViewModel extends AndroidViewModel {
    UsersRepository usersRepository;

    public SettingsViewModel(Application app) {
        super(app);
        usersRepository = UsersRepository.getInstance(app);
    }

    public void init (){
        String userId = usersRepository.getCurrentUser().getValue().getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return usersRepository.getCurrentUser();
    }

    public void signOut() {
        usersRepository.signOut();
    }
}