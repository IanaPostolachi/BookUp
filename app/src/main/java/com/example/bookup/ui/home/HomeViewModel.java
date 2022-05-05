package com.example.bookup.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.Repos.BooksRepository;
import com.example.bookup.Repos.UsersRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    private static HomeViewModel instance;
    private BooksRepository booksRepository;
    private UsersRepository usersRepository;

    public HomeViewModel(Application app)
    {
        super(app);
        booksRepository =BooksRepository.getInstance();
        usersRepository = UsersRepository.getInstance(app);
    }

    public LiveData<ArrayList<Book>> getSearchedBooks()
    {
        return booksRepository.getSearchedBooksFromApi();
    }

    public void searchBooks(String name)
    {
        booksRepository.searchBookInApi(name);
    }

    public LiveData<Integer> getTotalItemsSearched()
    {
        return booksRepository.getTotalItemsSearchedFromApi();
    }

    public void init (){
        String userId = usersRepository.getCurrentUser().getValue().getUid();
        booksRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return usersRepository.getCurrentUser();
    }
}