package com.example.bookup.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookup.MainActivity;
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
    private TextView noBooks;
    private ProgressBar progressBar;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        isLoading.setValue(true);
        searchedBooks = findViewById(R.id.Books);
        backButton = findViewById(R.id.back_button);
        progressBar = findViewById(R.id.progressBarForSearch);
        noBooks = findViewById(R.id.no_books);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        this.gson = new Gson();
        noBooks.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        String searchKeyWord = bundle.getString("searchKeyWord");

        homeViewModel.searchBooks(searchKeyWord);
        homeViewModel.getSearchedBooks().observe(this, books -> {
            if (!books.isEmpty()) {
                noBooks.setVisibility(View.INVISIBLE);
                booksToDisplay = new ArrayList<>();
                searchedBooks.hasFixedSize();
                searchedBooks.setLayoutManager(new LinearLayoutManager(this));
                for (int i = 0; i < books.size(); i++) {
                    booksToDisplay.add(books.get(i));
                }
                isLoading.setValue(false);
                BooksAdapter adapter = new BooksAdapter(books, getBaseContext(), this);
                searchedBooks.setAdapter(adapter);

            } else {
                BooksAdapter adapter = new BooksAdapter(new ArrayList<>(), getBaseContext(), this);
                searchedBooks.setAdapter(adapter);
                isLoading.setValue(false);
                noBooks.setVisibility(View.VISIBLE);
            }
        });

        isLoading().observe(this, isLoading -> {
            int visibility = isLoading ? View.VISIBLE : View.INVISIBLE;
            progressBar.setVisibility(visibility);
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });
    }

    public void goBack(View v) {
//        Intent intent = new Intent();
//        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, SpecificBookActivity.class);
        String toNewView = gson.toJson(booksToDisplay.get(clickedItemIndex));
        int clicked = clickedItemIndex;
        intent.putExtra("book", toNewView);
        intent.putExtra("position", clickedItemIndex);

        startActivityForResult(intent, 1);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}