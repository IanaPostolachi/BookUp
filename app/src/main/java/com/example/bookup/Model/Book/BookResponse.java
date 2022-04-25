package com.example.bookup.Model.Book;

import java.util.ArrayList;

public class BookResponse {

    ArrayList<Book> items;

    public ArrayList<Book> getBooks()
    {
        return new ArrayList<Book>(items);
    }
}
