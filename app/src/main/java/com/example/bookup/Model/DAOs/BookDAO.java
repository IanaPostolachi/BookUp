package com.example.bookup.Model.DAOs;

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
                    BookList bookList = getBookList(child.getKey());
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

    public BookList getBookList(String id) {
        BookList bookList;

        switch (id) {
            case "read_later":
                bookList = new BookList("Read later");
                bookList.setId(id);
                bookList.setImage(R.drawable.read_later_icon);
                break;
            case "favorite":
                bookList = new BookList("Favorites");
                bookList.setId(id);
                bookList.setImage(R.drawable.final_heart);
                break;
            default:
                bookList = new BookList("Unknown");
                bookList.setImage(R.drawable.ic_unknown);
        }

//        reference.child(id).orderByChild("personalRating").addValueEventListener(new ValueEventListener() {
//
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                movieList.getList().clear();
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
//                    Movie movie = child.getValue(Movie.class);
//                    movie.setId(child.getKey());
//                    movieList.setId(dataSnapshot.getKey());
//                    movieList.addMovie(movie);
//                }
//                Collections.reverse(movieList.getList());
//            }
//
//            public void onCancelled(DatabaseError databaseError) {}
//        });

        return bookList;
    }
}
