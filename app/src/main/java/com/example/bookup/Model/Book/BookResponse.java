package com.example.bookup.Model.Book;

import java.util.ArrayList;

public class BookResponse {

    ArrayList<Book> items;
    int totalItems;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<Book> getBooks()
    {
        return new ArrayList<Book>(items);
    }
}
