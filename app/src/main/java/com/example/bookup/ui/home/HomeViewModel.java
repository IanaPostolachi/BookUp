package com.example.bookup.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.Repos.BooksRepository;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private static HomeViewModel instance;
    private BooksRepository booksRepository;

    public HomeViewModel()
    {
        booksRepository =BooksRepository.getInstance();
    }

    public static synchronized HomeViewModel getInstance() {
        if (instance == null) {
            instance = new HomeViewModel();
        }
        return instance;
    }

    public LiveData<ArrayList<Book>> getSearchedBooks()
    {
        return booksRepository.getSearchedBooks();
    }

    public LiveData<ArrayList<Book>> searchBooks(String name)
    {
        return booksRepository.searchBook(name);
    }

}