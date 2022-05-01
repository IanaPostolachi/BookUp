package com.example.bookup.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;
import com.google.gson.Gson;

public class SpecificBookActivity extends AppCompatActivity {

    private LinearLayout back;
    private Gson gson = new Gson();
    private Book book;
    private ImageView bookImage;
    private TextView title;
    private TextView yearPublished;
    private TextView pageCount;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_book);
        back = findViewById(R.id.back_button_2);


        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("book");
        book = gson.fromJson(data, Book.class);

        bookImage = findViewById(R.id.imageForTheBook);
        Glide.with(this)
                .load(book.getVolumeInfo().getImageLinks().getThumbnail())
                .into(bookImage);

        title = findViewById(R.id.specificTitle);
        title.setText(book.getVolumeInfo().getTitle());

        yearPublished = findViewById(R.id.yearPublished);
        yearPublished.setText(book.getVolumeInfo().getPublishedDate());

        pageCount = findViewById(R.id.pages);
        pageCount.setText(book.getVolumeInfo().getPages()+"");

        description = findViewById(R.id.description);
        description.setText(book.getVolumeInfo().getDescription());


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