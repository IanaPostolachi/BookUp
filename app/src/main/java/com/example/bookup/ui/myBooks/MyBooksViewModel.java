package com.example.bookup.ui.myBooks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookList;
import com.example.bookup.Repos.BooksRepository;

import java.util.ArrayList;

public class MyBooksViewModel extends ViewModel
{
    private static MyBooksViewModel instance;
    private BooksRepository repository;

    private MyBooksViewModel()
    {
        repository = BooksRepository.getInstance();
    }

    public static synchronized MyBooksViewModel getInstance() {
        if (instance == null) {
            instance = new MyBooksViewModel();
        }
        return instance;
    }


    public void remove(String listId, String id) {
        repository.remove(listId, id);
    }

    public void saveMovie(String listId, Book bookToSave) {
        repository.saveMovie(listId, bookToSave);
    }

    public LiveData<ArrayList<BookList>> getAllListsFromDB() {
        return repository.getAllListsFromDB();
    }

    public String getJustDeletedBookId() {
        return repository.getJustDeletedBookId();
    }
}
