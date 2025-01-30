package com.project.fifamanagerdata;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.fifamanagerdata.navigation.FeeCalculation;
import com.project.fifamanagerdata.navigation.SearchMatchHistory;
import com.project.fifamanagerdata.navigation.FavoritesPlayer;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SearchMatchHistory searchMatchHistory;
    private FavoritesPlayer favoritesPlayer;
    private FeeCalculation feeCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bnView = (BottomNavigationView) findViewById(R.id.bottomNav);
        searchMatchHistory = new SearchMatchHistory();
        favoritesPlayer = new FavoritesPlayer();
        feeCalculation = new FeeCalculation();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.search_match_history) {
                    setView(0);
                } else if (item.getItemId() == R.id.Favorites_player) {
                    setView(1);
                } else if (item.getItemId() == R.id.fee_calculation) {
                    setView(2);
                }
                return true;
            }
        });

        setView(0);

    }


    private void setView(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (n) {
            case 0:
                fragmentTransaction.replace(R.id.main_frame, searchMatchHistory);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, favoritesPlayer);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame, feeCalculation);
                fragmentTransaction.commit();
                break;
        }

    }

}