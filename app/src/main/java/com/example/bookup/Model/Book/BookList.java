package com.example.bookup.Model.Book;

import java.util.ArrayList;

public class BookList {
    private String name;
    private String id;
    private int image;
    private ArrayList<Book> books;

    public BookList(String name)
    {
        this.name = name;
        books = new ArrayList<>();
    }

    public BookList() {
        this.books = new ArrayList<>();
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public int getSize()
    {
        return books.size();
    }

    public void addBook(Book book)
    {
        books.add(book);
    }

    public boolean isEmpty()
    {
        return books.isEmpty();
    }
}
