package com.example.bookup.ui.myBooks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookup.Model.Book.BookList;
import com.example.bookup.R;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MyBooksFragment extends Fragment implements TypeOfListAdapter.OnListItemClickListener {

    private MyBooksViewModel viewModel;
    private Gson gson;
    private ArrayList<BookList> bookLists;
    private RecyclerView myLists;
    private ProgressBar progressBar;
    private TextView noBooksMessage;
    private String name;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);

    private MutableLiveData<ArrayList<BookList>> books = new MutableLiveData<>();


    public MyBooksFragment() {
        this.gson = new Gson();
        bookLists = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_books, container, false);
        viewModel = MyBooksViewModel.getInstance();
        isLoading.setValue(true);


        progressBar = view.findViewById(R.id.progress_bar_my_books);
        myLists = view.findViewById(R.id.my_books_list);
        noBooksMessage = view.findViewById(R.id.no_my_books);
        noBooksMessage.setVisibility(View.INVISIBLE);
        myLists.hasFixedSize();

        viewModel.getAllListsFromDB().observe(getViewLifecycleOwner(), theList -> {
            if (bookLists.isEmpty()) {
                for (BookList bookList : theList) {
                    if (bookList.getId() != null) {
                        switch (bookList.getId()) {
                            case "read_later":
                                name = getResources().getString(R.string.ReadLater);
                                bookList.setName(name);
                                break;
                            case "favorite":
                                name = getResources().getString(R.string.Favourites);
                                bookList.setName(name);
                                break;
                            default:
                                bookList = new BookList("Unknown");
                                bookList.setImage(R.drawable.ic_unknown);
                        }
                    }
                    bookLists.add(bookList);
                }
            }
            books.setValue(bookLists);
        });

        getBooks().observe(getViewLifecycleOwner(), listOfBookLists -> {
            if (listOfBookLists.size() == 0) {
                noBooksMessage.setVisibility(View.VISIBLE);
                isLoading.setValue(false);
            } else {
                noBooksMessage.setVisibility(View.INVISIBLE);
                isLoading.setValue(false);
            }

//            LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            myLists.setLayoutManager(new LinearLayoutManager(getContext()));
            TypeOfListAdapter adapter = new TypeOfListAdapter(listOfBookLists, this);
            myLists.setAdapter(adapter);
        });

        isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            int visibility = isLoading ? View.VISIBLE : View.INVISIBLE;
            progressBar.setVisibility(visibility);
        });

        return view;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public MutableLiveData<ArrayList<BookList>> getBooks() {
        return books;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (bookLists.get(clickedItemIndex).isEmpty()) {
            Toast.makeText(getActivity(), "Nothing here yet", Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}