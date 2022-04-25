package com.example.bookup.GoogleBooksApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksServicesGenerator {

private static BooksApi booksApi;

    public static BooksApi getBooksAPI() {
        if (booksApi == null) {
            booksApi = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BooksApi.class);
        }
        return booksApi;
    }
}
