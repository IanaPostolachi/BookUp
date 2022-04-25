package com.example.bookup.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;
import com.example.bookup.ui.myBooks.TypeOfListAdapter;

import java.util.ArrayList;

public class BooksFragmentAdapter extends RecyclerView.Adapter<BooksFragmentAdapter.ViewHolder> {

    private ArrayList<Book> books;

    public BooksFragmentAdapter(ArrayList<Book> books)
    {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_books,parent,false);
        return new BooksFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView yearPublished;
        TextView author;
        ImageView booksImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            yearPublished = itemView.findViewById(R.id.YearPublished);
            author = itemView.findViewById(R.id.Author);
            booksImage = itemView.findViewById(R.id.bookImage);
        }
    }
}
