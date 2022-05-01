package com.example.bookup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookup.ui.home.BooksActivity;
import com.example.bookup.ui.home.HomeViewModel;
import com.example.bookup.ui.myBooks.TypeOfListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.bookup.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TypeOfListAdapter typeOfListAdapter;
    private HomeViewModel homeViewModel;
    private Button searchButton;
    private EditText searchBooksBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchButton = findViewById(R.id.search_button);
        searchBooksBar = findViewById(R.id.search_bar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BooksActivity.class);
                intent.putExtra("searchKeyWord", searchBooksBar.getText().toString());
                startActivityForResult(intent,1);
                searchBooksBar.setText("");
            }
        });
    }

}