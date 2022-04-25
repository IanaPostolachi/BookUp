package com.example.bookup.GoogleBooksApi;


import com.example.bookup.Model.Book.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksApi {

    @GET("books/v1/volumes?")
    Call<BookResponse> searchBooks(@Query("q") String nameOfThe);
}
