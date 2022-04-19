package com.example.bookup.ui.books;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookup.R;
import com.example.bookup.databinding.FragmentDashboardBinding;
import com.example.bookup.ui.dashboard.DashboardViewModel;


public class BooksFragment extends Fragment {

    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        BooksViewModel booksViewModel =
                new ViewModelProvider(this).get(BooksViewModel.class);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);
    }
}