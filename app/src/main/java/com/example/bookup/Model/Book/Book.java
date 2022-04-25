package com.example.bookup.Model.Book;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Book {
    private String id;
    private String title;
    private ArrayList<String> authors;
    @SerializedName("pageCount")
    private int pages;
    private String publisher;
    private String publishedDate;

    public Book()
    {

    }

    public Book(String id, String title, ArrayList<String> authors, int pages, String publisher, String publishedDate) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<String> getAuthor() {
        return authors;
    }

    public void setAuthor(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
