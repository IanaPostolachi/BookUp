package com.example.bookup.ui.myBooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;
import com.google.gson.Gson;

public class OneBookMyListActivity extends AppCompatActivity {

    private TextView myTitle;
    private TextView myYear;
    private TextView myPages;
    private TextView myDescription;
    private ImageView myImage;
    private Button remove;
    private LinearLayout back;
    private Book book;
    private Gson gson = new Gson();
    private MyBooksViewModel myBooksViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_book_my_list);

        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("myBook");
        String listId = bundle.getString("listId");
        book = gson.fromJson(data, Book.class);
        myBooksViewModel = MyBooksViewModel.getInstance();


        myTitle = findViewById(R.id.myTitle);
        myTitle.setText(book.getVolumeInfo().getTitle());

        myYear = findViewById(R.id.myYearPublished);
        myYear.setText(book.getVolumeInfo().getPublishedDate());

        myPages = findViewById(R.id.myPages);
        myPages.setText(book.getVolumeInfo().getPages() + "");

        myDescription = findViewById(R.id.myDescription);
        myDescription.setText(book.getVolumeInfo().getDescription());

        myImage = findViewById(R.id.imageForMyBook);
        if (book.getVolumeInfo().getImageLinks() != null) {
            Glide.with(this)
                    .load(book.getVolumeInfo().getImageLinks().getThumbnail())
                    .into(myImage);
        }
        else {
            myImage.setImageResource(R.drawable.no_image_available_iconjpg);
        }

//        remove = findViewById(R.id.remove);
//        remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myBooksViewModel.remove(listId,book.getId());
//            }
//        });

        back = findViewById(R.id.back_button_my_one_book);
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