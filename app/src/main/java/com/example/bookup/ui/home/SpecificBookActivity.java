package com.example.bookup.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;
import com.example.bookup.ui.myBooks.MyBooksViewModel;
import com.google.gson.Gson;

public class SpecificBookActivity extends AppCompatActivity {

    private LinearLayout back;
    private Gson gson = new Gson();
    private Book book;
    private Button addToReadLater;
    private ImageView addToFav;
    private ImageView bookImage;
    private TextView title;
    private TextView yearPublished;
    private TextView pageCount;
    private TextView description;
    private MyBooksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_book);
        viewModel = MyBooksViewModel.getInstance();
        back = findViewById(R.id.back_button_2);


        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("book");
        int index = bundle.getInt("position");
        book = gson.fromJson(data, Book.class);

        bookImage = findViewById(R.id.imageForTheBook);
        if (book.getVolumeInfo().getImageLinks() != null) {
            Glide.with(this)
                    .load(book.getVolumeInfo().getImageLinks().getThumbnail())
                    .into(bookImage);
        }
        else {
            bookImage.setImageResource(R.drawable.no_image_available_iconjpg);
        }

        title = findViewById(R.id.specificTitle);
        title.setText(book.getVolumeInfo().getTitle());

        yearPublished = findViewById(R.id.yearPublished);
        yearPublished.setText(book.getVolumeInfo().getPublishedDate());

        pageCount = findViewById(R.id.pages);
        pageCount.setText(book.getVolumeInfo().getPages()+"");

        description = findViewById(R.id.description);
        description.setText(book.getVolumeInfo().getDescription());

        addToFav = findViewById(R.id.AddToFav);
        addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveMovie("favorite",book);
                Toast.makeText(getBaseContext(),book.getVolumeInfo().getTitle() + " added to favourites",Toast.LENGTH_LONG).show();
            }
        });

        addToReadLater = findViewById(R.id.AddToReadLater);
        addToReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (!viewModel.getAllListsFromDB().getValue().get(index).getBooks().get(index).getVolumeInfo().getTitle().equals(book.getVolumeInfo().getTitle())) {
                    viewModel.saveMovie("read_later", book);
                    Toast.makeText(getBaseContext(), book.getVolumeInfo().getTitle() + " added to Read later", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    Toast.makeText(getBaseContext(), book.getVolumeInfo().getTitle() + " already on the list", Toast.LENGTH_LONG).show();
//                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
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
}