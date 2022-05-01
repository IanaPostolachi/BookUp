package com.example.bookup.Model.Book;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VolumeInfo {
    private ArrayList<String> authors;
    private ImageLinks imageLinks;
    @SerializedName("pageCount")
    private int pages;
    private String publisher;
    private String publishedDate;
    private String title;
    private String description;


    public VolumeInfo()
    {

    }

    public VolumeInfo(ArrayList<String> authors, int pages, String publisher, String publishedDate, String title, String description, ImageLinks imageLinks)
    {
        this.authors = authors;
        this.pages = pages;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.title = title;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
