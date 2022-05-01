package com.example.bookup.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;
import com.google.gson.Gson;

import java.util.ArrayList;


public class BooksActivity extends AppCompatActivity implements BooksAdapter.OnListItemClickListener {

    private HomeViewModel homeViewModel;
    private RecyclerView searchedBooks;
    private Gson gson;
    private ArrayList<Book> booksToDisplay;
    private LinearLayout backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        searchedBooks = findViewById(R.id.Books);
        backButton = findViewById(R.id.back_button);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        this.gson = new Gson();

        Bundle bundle = getIntent().getExtras();
        String searchKeyWord = bundle.getString("searchKeyWord");

        homeViewModel.searchBooks(searchKeyWord);
        homeViewModel.getSearchedBooks().observe(this,books ->{
            booksToDisplay = new ArrayList<>();
            searchedBooks.hasFixedSize();
            searchedBooks.setLayoutManager(new LinearLayoutManager(this));
            for (int i = 0; i < books.size(); i++)
            {
                booksToDisplay.add(books.get(i));
            }
            BooksAdapter adapter = new BooksAdapter(books, getBaseContext(),this);
            searchedBooks.setAdapter(adapter);
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });
    }

    public void goBack(View v)
    {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, SpecificBookActivity.class);
        String toNewView = gson.toJson(booksToDisplay.get(clickedItemIndex));
        intent.putExtra("book", toNewView);

        startActivityForResult(intent, 1);
    }
}