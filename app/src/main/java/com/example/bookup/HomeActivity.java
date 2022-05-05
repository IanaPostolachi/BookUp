package com.example.bookup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookup.databinding.ActivityHomeBinding;
import com.example.bookup.databinding.ActivityMainBinding;
import com.example.bookup.ui.home.BooksActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private Button searchButton;
    private EditText searchBooksBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchButton = findViewById(R.id.search_button);
        searchBooksBar = findViewById(R.id.search_bar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
//        navView.setBackgroundColor(getResources().getColor(R.color.nav_bar_color));
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BooksActivity.class);
                intent.putExtra("searchKeyWord", searchBooksBar.getText().toString());
                startActivityForResult(intent,1);
                searchBooksBar.setText("");
            }
        });
    }
}