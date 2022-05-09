package com.example.bookup.ui.myBooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.Model.Book.BookList;
import com.example.bookup.R;
import com.example.bookup.ui.home.SpecificBookActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SelectedListActivity extends AppCompatActivity implements SelectedListAdapter.OnListItemClickListener {

    private ArrayList<Book> myBooks;
    private RecyclerView booksView;
    private Gson gson;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private ProgressBar progressBar;
    private LinearLayout back;
    private TextView listName;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list);
        booksView = findViewById(R.id.booksFromMyList);
        progressBar = findViewById(R.id.progressBarForMyBooks);
        back = findViewById(R.id.back_button_for_my_list);
        listName = findViewById(R.id.listName);
        gson = new Gson();
        isLoading.setValue(true);


        Bundle bundle = getIntent().getExtras();
        String received= bundle.getString("myBooks");
        BookList books = gson.fromJson(received, BookList.class);
        if (books.getName().equals("Read later"))
        {
            listName.setText(R.string.ReadLater);
        }
        else if (books.getName().equals("Favourites"))
        {
            listName.setText(R.string.Favourites);
        }

        myBooks = books.getBooks();

        booksView.hasFixedSize();
        booksView.setLayoutManager(new LinearLayoutManager(this));
        SelectedListAdapter adapter = new SelectedListAdapter(myBooks,this,this);
        booksView.setAdapter(adapter);
        isLoading.setValue(false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack(view);
            }
        });

        isLoading().observe(this, isLoading -> {
            int visibility = isLoading ? View.VISIBLE : View.INVISIBLE;
            progressBar.setVisibility(visibility);
        });
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void goBack(View v) {
        finish();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, OneBookMyListActivity.class);
        String toNewView = gson.toJson(myBooks.get(clickedItemIndex));
        intent.putExtra("myBook", toNewView);

        startActivityForResult(intent, 1);
    }
}