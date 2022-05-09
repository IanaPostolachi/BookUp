package com.example.bookup.ui.myBooks;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookup.Model.Book.BookList;
import com.example.bookup.R;

import java.util.ArrayList;

public class TypeOfListAdapter extends RecyclerView.Adapter<TypeOfListAdapter.ViewHolder> {

    private ArrayList<BookList> list;
    final private OnListItemClickListener mOnListItemClickListener;

    public TypeOfListAdapter(ArrayList<BookList> list, OnListItemClickListener onListItemClickListener)
    {
        this.list = list;
        mOnListItemClickListener = onListItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.type_of_lists,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listName.setText(list.get(position).getName());
        holder.listImage.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView listName;
        ImageView listImage;

        @SuppressLint("UseCompatLoadingForDrawables")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listImage = itemView.findViewById(R.id.listImage);
            listName = itemView.findViewById(R.id.listName);
            itemView.setOnClickListener(this);
            listImage.setColorFilter(R.color.black);
        }

        @Override
        public void onClick(View view) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
