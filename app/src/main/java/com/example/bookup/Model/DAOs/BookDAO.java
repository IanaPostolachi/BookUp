package com.example.bookup.Model.DAOs;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bookup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookList;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAO extends LiveData<Book> {
    private DatabaseReference reference;
    private final MutableLiveData<BookList> selectedBookList;
    private final MutableLiveData<ArrayList<BookList>> allBookLists;
    private String deletedBook = "";

    public BookDAO(String userId) {
        selectedBookList = new MutableLiveData<>();
        allBookLists = new MutableLiveData<>();
        reference = FirebaseDatabase.getInstance("https://bookup-727f0-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference().child("users").child(userId);
    }

    public void remove(String listId, String id) {
        reference.child(listId).child(id).removeValue();
    }

    public void saveBook(String listId, Book bookToSave) {
        deletedBook = reference.child(listId).push().getKey();
        reference.child(listId).child(deletedBook).setValue(bookToSave);
    }

    public String getJustDeletedBookId() {
        return deletedBook;
    }

    public LiveData<ArrayList<BookList>> getAllListsFromDB() {
        ArrayList<BookList> allLists = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                allLists.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    BookList bookList = getBookList(child);
                    allLists.add(bookList);
                }
                allBookLists.setValue(allLists);
            }

            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });

        return allBookLists;
    }

    public BookList getBookList(DataSnapshot datasnapshot) {
        BookList bookList;

        switch (datasnapshot.getKey()) {
            case "read_later":
                bookList = new BookList("Read later");
                bookList.setId(datasnapshot.getKey());
                bookList.setImage(R.drawable.bookmark);
                ArrayList<Book> readLaterBooks = new ArrayList<>();
                for (DataSnapshot child : datasnapshot.getChildren()) {
                    Book book = child.getValue(Book.class);
                    book.setId(child.getKey());
                    readLaterBooks.add(book);
                }
                bookList.setBooks(readLaterBooks);
                break;
            case "favorite":
                bookList = new BookList("Favorites");
                bookList.setId(datasnapshot.getKey());
                bookList.setImage(R.drawable.heart_book);
                ArrayList<Book> favoriteBooks = new ArrayList<>();
                for (DataSnapshot child : datasnapshot.getChildren()) {
                    Book book = child.getValue(Book.class);
                    book.setId(child.getKey());
                    favoriteBooks.add(book);
                }
                bookList.setBooks(favoriteBooks);
                break;
            default:
                bookList = new BookList("Unknown");
                bookList.setImage(R.drawable.ic_unknown);
        }

        return bookList;
    }
}
