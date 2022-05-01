package com.example.bookup.Model.DAOs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.database.DatabaseReference;


import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookList;

import java.util.ArrayList;

public class BookDAO extends LiveData<Book> {
    private DatabaseReference reference;
    private final MutableLiveData<BookList> selectedBookList;
    private final MutableLiveData<ArrayList<BookList>> allBookLists;

    public BookDAO(String userId) {
        selectedBookList = new MutableLiveData<>();
        allBookLists = new MutableLiveData<>();
//        myRef = FirebaseDatabase.getInstance("")
//                .getReference().child("users").child(userId);
    }


}
