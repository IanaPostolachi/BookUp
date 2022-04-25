package com.example.bookup.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bookup.Model.Book.Book;
import com.example.bookup.R;

import java.util.ArrayList;

public class BooksFragment extends Fragment {
    private RecyclerView searchedBooks;
    private HomeViewModel homeViewModel;
    private ArrayList<Book> booksToDisplay;


    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_books, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        searchedBooks = root.findViewById(R.id.Books);

        homeViewModel.getSearchedBooks().observe(getViewLifecycleOwner(),books ->{
            booksToDisplay = new ArrayList<>();
            searchedBooks.hasFixedSize();
            searchedBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
            for (int i = 0; i < books.size(); i++)
            {
                booksToDisplay.add(books.get(i));
            }
            BooksFragmentAdapter adapter = new BooksFragmentAdapter(books);
            searchedBooks.setAdapter(adapter);
        });
        return root;
    }

    public static BooksFragment newInstance()
    {
        return new BooksFragment();
    }


}