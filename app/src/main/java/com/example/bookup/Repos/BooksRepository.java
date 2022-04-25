package com.example.bookup.Repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookup.GoogleBooksApi.BooksApi;
import com.example.bookup.GoogleBooksApi.BooksServicesGenerator;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BooksRepository {
    private static BooksRepository instance;
    private final MutableLiveData<ArrayList<Book>> searchedBooks;

    public BooksRepository()
    {
        searchedBooks = new MutableLiveData<>();
    }

    public static synchronized BooksRepository getInstance() {
        if (instance == null) {
            instance = new BooksRepository();
        }
        return instance;
    }



    public LiveData<ArrayList<Book>> searchBook(String name) {
        BooksApi booksApi = BooksServicesGenerator.getBooksAPI();
        Call<BookResponse> call = booksApi.searchBooks(name);
        call.enqueue(new Callback<BookResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {
                    searchedBooks.setValue(response.body().getBooks());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
        return searchedBooks;
    }

    public MutableLiveData<ArrayList<Book>> getSearchedBooks() {
        return searchedBooks;
    }
}
