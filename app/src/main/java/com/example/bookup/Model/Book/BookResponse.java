package com.example.bookup.Model.Book;

import java.util.ArrayList;

public class BookResponse {

    String id;
    ArrayList<Book> items;
    int totalItems;

    public ArrayList<Book> getItems() {
        return items;
    }

    public void setItems(ArrayList<Book> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
