package com.example.bookup.Repos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookup.GoogleBooksApi.BooksApi;
import com.example.bookup.GoogleBooksApi.BooksServicesGenerator;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookList;
import com.example.bookup.Model.Book.BookResponse;
import com.example.bookup.Model.DAOs.BookDAO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BooksRepository {
    private static BooksRepository instance;
    private final MutableLiveData<ArrayList<Book>> searchedBooks;
    private final MutableLiveData<Integer> totalItemsSearched;
    private BookDAO bookDAO;

    public BooksRepository()
    {
        searchedBooks = new MutableLiveData<>();
        totalItemsSearched = new MutableLiveData<>();
    }

    public static synchronized BooksRepository getInstance() {
        if (instance == null) {
            instance = new BooksRepository();
        }
        return instance;
    }

    public void init(String userId)  {
        bookDAO = new BookDAO(userId);
    }

    public void remove(String listId, String id) {
        bookDAO.remove(listId, id);
    }

    public void saveMovie(String listId, Book bookToSave) {
        bookDAO.saveBook(listId, bookToSave);
    }

    public LiveData<ArrayList<BookList>> getAllListsFromDB() {
        return bookDAO.getAllListsFromDB();
    }

    public String getJustDeletedBookId() {
        return bookDAO.getJustDeletedBookId();
    }

    public void searchBookInApi(String name) {
        BooksApi booksApi = BooksServicesGenerator.getBooksAPI();
        Call<BookResponse> call = booksApi.searchBooks(name);
        call.enqueue(new Callback<BookResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful()) {


                    searchedBooks.setValue(response.body().getTotalItems() != 0 ? response.body().getBooks() : new ArrayList<>());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public MutableLiveData<ArrayList<Book>> getSearchedBooksFromApi() {
        return searchedBooks;
    }

    public MutableLiveData<Integer> getTotalItemsSearchedFromApi() {
        return totalItemsSearched;
    }
}
