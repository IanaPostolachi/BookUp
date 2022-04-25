package com.example.bookup.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookup.MainActivity;
import com.example.bookup.R;
import com.example.bookup.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private EditText searchBooksBar;
    private RecyclerView myListsView;
    private Button searchButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        searchBooksBar = root.findViewById(R.id.search_bar);
        myListsView = root.findViewById(R.id.my_books_list);
        searchButton = root.findViewById(R.id.search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.searchBooks(searchBooksBar.getText().toString());
                displayFragment();
            }
        });
        return root;
    }

    public void displayFragment()
    {
        BooksFragment booksFragment = BooksFragment.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.booksContainer,booksFragment).addToBackStack(null).commit();
    }
}