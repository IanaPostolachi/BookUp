package com.example.bookup.Model.Book;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Book {
    private String id;
    private VolumeInfo volumeInfo;

    public Book()
    {

    }

    public Book(String id, VolumeInfo volumeInfo) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }


    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

}
