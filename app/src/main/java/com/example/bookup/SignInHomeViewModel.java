package com.example.bookup;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookup.Repos.UsersRepository;
import com.google.firebase.auth.FirebaseUser;

public class SignInHomeViewModel extends AndroidViewModel {
    private UsersRepository usersRepository;


    public SignInHomeViewModel(Application app) {
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
