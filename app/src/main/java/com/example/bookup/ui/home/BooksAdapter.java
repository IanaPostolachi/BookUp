package com.example.bookup.ui.home;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<Book> books;
    private Context context;
    private OnListItemClickListener onListItemClickListener;

    public BooksAdapter(ArrayList<Book> books, Context context, OnListItemClickListener onListItemClickListener) {
        this.books = books;
        this.context = context;
        this.onListItemClickListener = onListItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.one_book, parent, false);
        return new BooksAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (books.get(position).getVolumeInfo().getPublishedDate() != null) {
            holder.yearPublished.setText(books.get(position).getVolumeInfo().getPublishedDate());
        }
        else {
            holder.yearPublished.setText(R.string.no_date);
        }

        String authors = "";
        if (books.get(position).getVolumeInfo().getAuthors() != null) {
            authors = String.join(", ", books.get(position).getVolumeInfo().getAuthors());
            holder.author.setText(authors);
        }
        else
        {
            holder.author.setText(R.string.no_authors);
        }

        holder.title.setText(books.get(position).getVolumeInfo().getTitle());

        if (books.get(position).getVolumeInfo().getImageLinks() != null) {
            Glide.with(context)
                    .load(books.get(position).getVolumeInfo().getImageLinks().getSmallThumbnail())
                    .into(holder.booksImage);
        }
        else
        {
            holder.booksImage.setImageResource(R.drawable.no_image_available_iconjpg);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
